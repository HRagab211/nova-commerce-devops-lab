package com.hossam.novacommerce.order;

import com.hossam.novacommerce.common.BusinessRuleException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commerce_orders", indexes = {
        @Index(name = "idx_orders_tenant", columnList = "tenant_id"),
        @Index(name = "idx_orders_customer", columnList = "customer_id")
})
public class CommerceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenant_id", nullable = false)
    private Long tenantId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OrderStatus status = OrderStatus.CREATED;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id", nullable = false)
    private List<OrderItem> items = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected CommerceOrder() {
    }

    public CommerceOrder(Long tenantId, Long customerId) {
        this.tenantId = tenantId;
        this.customerId = customerId;
    }

    public Long getId() { return id; }
    public Long getTenantId() { return tenantId; }
    public Long getCustomerId() { return customerId; }
    public OrderStatus getStatus() { return status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public List<OrderItem> getItems() { return List.copyOf(items); }
    public Instant getCreatedAt() { return createdAt; }

    public void addItem(OrderItem item) {
        items.add(item);
        totalAmount = totalAmount.add(item.getLineTotal());
    }

    public void markPaid() {
        if (status != OrderStatus.CREATED) {
            throw new BusinessRuleException("Only CREATED orders can be paid");
        }
        status = OrderStatus.PAID;
    }
}
