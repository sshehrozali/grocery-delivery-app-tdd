package com.ordering.app.dto;

import lombok.Builder;

@Builder
public record ItemDTO(
        String id,
        String name,
        String description,
        Float price
) {
}
