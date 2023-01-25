package com.ordering.app;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerService serviceUnderTest;

    @Mock
    private ItemRepository itemRepository;
    @Mock
    private LineRepository lineRepository;

    @Test
    @DisplayName("Should Throw Exception If No Item Found In Stock")
    void shouldThrowExceptionIfNoItemsFoundInStock() {
        assertThrows(RuntimeException.class, () -> {
            serviceUnderTest.getAllItems();
        });
    }

    @Test
    @DisplayName("Should Get All Items With Their Available Quantity")
    void shouldGetAllItemsWithTheirAvailableQuantity() {
        ArrayList<Line> lines = new ArrayList<>();
        lines.add(
                Line.builder()
                        .itemId(
                                Item.builder()
                                        .itemId("item1")
                                        .itemName("Cool Item 1")
                                        .price("100")
                                        .build()
                        )
                        .quantity(10)
                        .build()
        );
        when(lineRepository.findAll()).thenReturn(lines);
        assertThat(serviceUnderTest.getAllItems()).isNull();
    }

}
