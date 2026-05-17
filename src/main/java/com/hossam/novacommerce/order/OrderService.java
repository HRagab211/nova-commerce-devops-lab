package com.hossam.novacommerce.order;

import com.hossam.novacommerce.common.BusinessRuleException;
import com.hossam.novacommerce.common.NotFoundException;
import com.hossam.novacommerce.config.RabbitMqConfig;
import com.hossam.novacommerce.customer.Customer;
import com.hossam.novacommerce.customer.CustomerService;
import com.hossam.novacommerce.event.DomainEventPublisher;
import com.hossam.novacommerce.event.OrderCreatedEvent;
import com.hossam.novacommerce.product.Product;
import com.hossam.novacommerce.product.ProductService;
import com.hossam.novacommerce.tenant.TenantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    private final CommerceOrderRepository orders;
    private final TenantService tenantService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final DomainEventPublisher events;

    public OrderService(CommerceOrderRepository orders,
                        TenantService tenantService,
                        CustomerService customerService,
                        ProductService productService,
                        DomainEventPublisher events) {
        this.orders = orders;
        this.tenantService = tenantService;
        this.customerService = customerService;
        this.productService = productService;
        this.events = events;
    }

    @Transactional
    public CommerceOrder create(OrderDto.CreateRequest request) {
        tenantService.get(request.tenantId());
        Customer customer = customerService.get(request.customerId());
        if (!Objects.equals(customer.getTenantId(), request.tenantId())) {
            throw new BusinessRuleException("Customer does not belong to tenant");
        }

        CommerceOrder order = new CommerceOrder(request.tenantId(), request.customerId());

        for (OrderDto.ItemRequest itemRequest : request.items()) {
            Product product = productService.get(itemRequest.productId());
            if (!Objects.equals(product.getTenantId(), request.tenantId())) {
                throw new BusinessRuleException("Product does not belong to tenant");
            }
            product.reserve(itemRequest.quantity());
            order.addItem(new OrderItem(product.getId(), product.getSku(), product.getName(), itemRequest.quantity(), product.getPrice()));
        }

        CommerceOrder saved = orders.save(order);
        events.publish(RabbitMqConfig.ORDER_ROUTING_KEY, new OrderCreatedEvent(
                saved.getId(), saved.getTenantId(), saved.getCustomerId(), saved.getTotalAmount(), saved.getCreatedAt()
        ));

        return saved;
    }

    @Transactional(readOnly = true)
    public CommerceOrder get(Long id) {
        return orders.findById(id).orElseThrow(() -> new NotFoundException("Order not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CommerceOrder> listByTenant(Long tenantId) {
        tenantService.get(tenantId);
        return orders.findByTenantIdOrderByCreatedAtDesc(tenantId);
    }

    @Transactional
    public CommerceOrder pay(Long id) {
        CommerceOrder order = get(id);
        order.markPaid();
        return order;
    }
}
