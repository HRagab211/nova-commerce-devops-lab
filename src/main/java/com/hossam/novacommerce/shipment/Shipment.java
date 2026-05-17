package com.hossam.novacommerce.shipment;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "shipments", indexes = {
        @Index(name = "idx_shipments_order", columnList = "order_id")
})
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(nullable = false, length = 80)
    private String carrier;

    @Column(nullable = false, length = 120)
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ShipmentStatus status = ShipmentStatus.PENDING;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    protected Shipment() {
    }

    public Shipment(Long orderId, String carrier, String trackingNumber) {
        this.orderId = orderId;
        this.carrier = carrier;
        this.trackingNumber = trackingNumber;
    }

    public Long getId() { return id; }
    public Long getOrderId() { return orderId; }
    public String getCarrier() { return carrier; }
    public String getTrackingNumber() { return trackingNumber; }
    public ShipmentStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }

    public void markDispatched() { this.status = ShipmentStatus.DISPATCHED; }
    public void markDelivered() { this.status = ShipmentStatus.DELIVERED; }
}
