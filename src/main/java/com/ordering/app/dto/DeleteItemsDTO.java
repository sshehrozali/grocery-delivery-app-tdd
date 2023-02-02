package com.ordering.app.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DeleteItemsDTO(
        List<String> itemIds
) {
}
