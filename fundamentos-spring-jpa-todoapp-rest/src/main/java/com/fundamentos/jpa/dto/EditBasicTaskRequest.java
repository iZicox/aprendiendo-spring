package com.fundamentos.jpa.dto;

public record EditBasicTaskRequest(
        String title,
        String description
) {
}
