package com.ordering.app.dto;

import lombok.Builder;

@Builder
public record LineDTO(
        ItemDTO itemId,
        Integer quantity
) {
}
