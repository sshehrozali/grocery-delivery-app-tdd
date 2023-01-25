package com.ordering.app;

import lombok.Builder;

@Builder
public record ItemDTO(
        String id,
        String name,
        String description,
        Float price
) {
}
