package com.hossam.novacommerce.shipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public final class ShipmentDto {
    public record CreateRequest(
            @NotNull Long orderId,
            @NotBlank @Size(max = 80) String carrier,
            @NotBlank @Size(max = 120) String trackingNumber
    ) {}

    public record Response(
            Long id,
            Long orderId,
            String carrier,
            String trackingNumber,
            ShipmentStatus status,
            Instant createdAt
    ) {
        public static Response from(Shipment shipment) {
            return new Response(shipment.getId(), shipment.getOrderId(), shipment.getCarrier(), shipment.getTrackingNumber(), shipment.getStatus(), shipment.getCreatedAt());
        }
    }
}
