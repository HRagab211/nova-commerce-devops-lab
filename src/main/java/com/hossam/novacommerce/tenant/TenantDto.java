package com.hossam.novacommerce.tenant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public final class TenantDto {
    public record CreateRequest(
            @NotBlank @Size(max = 80) String name,
            @NotBlank @Pattern(regexp = "^[a-z0-9-]{3,80}$") String slug,
            @NotBlank @Size(max = 160) String domain
    ) {
    }

    public record Response(
            Long id,
            String name,
            String slug,
            String domain,
            boolean active,
            Instant createdAt
    ) {
        public static Response from(Tenant tenant) {
            return new Response(
                    tenant.getId(),
                    tenant.getName(),
                    tenant.getSlug(),
                    tenant.getDomain(),
                    tenant.isActive(),
                    tenant.getCreatedAt()
            );
        }
    }
}
