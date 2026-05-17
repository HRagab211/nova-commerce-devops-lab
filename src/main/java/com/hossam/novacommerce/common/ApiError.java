package com.hossam.novacommerce.common;

import java.time.Instant;
import java.util.Map;

public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message,
        Map<String, String> validation
) {
    public static ApiError of(int status, String error, String message) {
        return new ApiError(Instant.now(), status, error, message, Map.of());
    }

    public static ApiError validation(Map<String, String> validation) {
        return new ApiError(Instant.now(), 422, "Validation failed", "Request validation failed", validation);
    }
}
