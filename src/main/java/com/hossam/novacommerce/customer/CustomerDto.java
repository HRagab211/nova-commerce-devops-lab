package com.hossam.novacommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public final class CustomerDto {
    public record CreateRequest(
            @NotNull Long tenantId,
            @NotBlank @Size(max = 120) String name,
            @NotBlank @Email @Size(max = 180) String email
    ) {}

    public record Response(
            Long id,
            Long tenantId,
            String name,
            String email,
            int loyaltyPoints,
            Instant createdAt
    ) {
        public static Response from(Customer customer) {
            return new Response(customer.getId(), customer.getTenantId(), customer.getName(), customer.getEmail(), customer.getLoyaltyPoints(), customer.getCreatedAt());
        }
    }
}
