package com.fundamentos.jpa.dto;

import com.fundamentos.jpa.model.CheckListItem;

public record CheckListItemResponse(
        Long id,
        String text,
        boolean checked
) {
    public static CheckListItemResponse of(CheckListItem item) {
        return new CheckListItemResponse(
                item.getId(),
                item.getText(),
                item.isChecked()
        );
    }
}
