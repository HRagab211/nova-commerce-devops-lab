package com.hossam.novacommerce.shipment;

import com.hossam.novacommerce.common.NotFoundException;
import com.hossam.novacommerce.order.OrderService;
import com.hossam.novacommerce.order.OrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShipmentService {
    private final ShipmentRepository shipments;
    private final OrderService orderService;

    public ShipmentService(ShipmentRepository shipments, OrderService orderService) {
        this.shipments = shipments;
        this.orderService = orderService;
    }

    @Transactional
    public Shipment create(ShipmentDto.CreateRequest request) {
        var order = orderService.get(request.orderId());
        if (order.getStatus() == OrderStatus.CREATED) {
            throw new com.hossam.novacommerce.common.BusinessRuleException("Order must be paid before shipment creation");
        }
        return shipments.save(new Shipment(request.orderId(), request.carrier(), request.trackingNumber()));
    }

    @Transactional(readOnly = true)
    public Shipment get(Long id) {
        return shipments.findById(id).orElseThrow(() -> new NotFoundException("Shipment not found: " + id));
    }

    @Transactional
    public Shipment dispatch(Long id) {
        Shipment shipment = get(id);
        shipment.markDispatched();
        return shipment;
    }

    @Transactional
    public Shipment deliver(Long id) {
        Shipment shipment = get(id);
        shipment.markDelivered();
        return shipment;
    }
}
