package com.spring.relaciones_1.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime time,
        String error,
        String message
) {
}
