package com.springboot.practica4.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        LocalDateTime time,
        String error,
        String mensaje
) {
}
