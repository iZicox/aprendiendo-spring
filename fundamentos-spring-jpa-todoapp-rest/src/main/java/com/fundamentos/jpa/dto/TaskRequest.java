package com.fundamentos.jpa.dto;

import java.util.List;

public record TaskRequest(
        String title,
        String descripcion,
        List<String> itemns,
        String username
) {
}
