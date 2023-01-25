package com.ordering.app;

import lombok.Builder;

@Builder
public record LineDTO(
        ItemDTO itemId,
        Integer quantity
) {
}
