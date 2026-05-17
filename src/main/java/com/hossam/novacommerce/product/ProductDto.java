package com.hossam.novacommerce.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;

public final class ProductDto {
    public record CreateRequest(
            @NotNull Long tenantId,
            @NotBlank @Size(max = 80) String sku,
            @NotBlank @Size(max = 180) String name,
            @NotNull @DecimalMin(value = "0.01") BigDecimal price,
            @Min(0) int stockQuantity
    ) {}

    public record Response(
            Long id,
            Long tenantId,
            String sku,
            String name,
            BigDecimal price,
            int stockQuantity,
            boolean active,
            Instant createdAt
    ) {
        public static Response from(Product product) {
            return new Response(product.getId(), product.getTenantId(), product.getSku(), product.getName(), product.getPrice(), product.getStockQuantity(), product.isActive(), product.getCreatedAt());
        }
    }
}
