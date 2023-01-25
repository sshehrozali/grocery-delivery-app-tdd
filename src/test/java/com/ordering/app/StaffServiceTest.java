package com.ordering.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StaffServiceTest {
    @InjectMocks
    private StaffService serviceUnderTest;
    @Mock
    private LineRepository lineRepository;

    @Test
    @DisplayName("Should Get All Items Available In Inventory With Quantity")
    void shouldGetAllItemsAvailableInInventoryWithQuantity() {
        ArrayList<Line> lines = new ArrayList<>();
        lines.add(
                Line.builder()
                        .itemId(
                                Item.builder()
                                        .itemId("item1")
                                        .itemName("Cool Item 1")
                                        .description("Amazing product")
                                        .price(12.4f)
                                        .cost(5.6f)
                                        .build()
                        )
                        .quantity(15)
                        .build()
        );
        when(lineRepository.findAll()).thenReturn(lines);

        assertThat(serviceUnderTest.getAllItemsInInventoryWithQuantity()).isEqualTo(lines);
    }
}
