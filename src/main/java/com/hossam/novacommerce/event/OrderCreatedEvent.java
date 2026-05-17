package com.hossam.novacommerce.event;

import java.math.BigDecimal;
import java.time.Instant;

public record OrderCreatedEvent(
        Long orderId,
        Long tenantId,
        Long customerId,
        BigDecimal totalAmount,
        Instant createdAt
) {}
