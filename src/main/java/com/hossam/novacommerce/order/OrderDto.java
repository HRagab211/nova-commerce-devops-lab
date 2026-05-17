package com.hossam.novacommerce.order;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public final class OrderDto {
    public record CreateRequest(
            @NotNull Long tenantId,
            @NotNull Long customerId,
            @NotEmpty List<@Valid ItemRequest> items
    ) {}

    public record ItemRequest(
            @NotNull Long productId,
            @Min(1) int quantity
    ) {}

    public record ItemResponse(
            Long productId,
            String sku,
            String productName,
            int quantity,
            BigDecimal unitPrice,
            BigDecimal lineTotal
    ) {
        public static ItemResponse from(OrderItem item) {
            return new ItemResponse(item.getProductId(), item.getSku(), item.getProductName(), item.getQuantity(), item.getUnitPrice(), item.getLineTotal());
        }
    }

    public record Response(
            Long id,
            Long tenantId,
            Long customerId,
            OrderStatus status,
            BigDecimal totalAmount,
            List<ItemResponse> items,
            Instant createdAt
    ) {
        public static Response from(CommerceOrder order) {
            return new Response(
                    order.getId(),
                    order.getTenantId(),
                    order.getCustomerId(),
                    order.getStatus(),
                    order.getTotalAmount(),
                    order.getItems().stream().map(ItemResponse::from).toList(),
                    order.getCreatedAt()
            );
        }
    }
}
