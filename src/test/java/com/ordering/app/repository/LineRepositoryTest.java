package com.ordering.app.repository;

import com.ordering.app.entity.Item;
import com.ordering.app.entity.Line;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LineRepositoryTest {

    @Autowired
    private LineRepository lineRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("Should Find Line By Item Id")
    void shouldFindLineByItemId() {
        Line alreadySavedLine = Line.builder()
                .itemId(
                        Item.builder()
                                .itemId("item1")
                                .itemName("Cool Item 1")
                                .description("Amazing product")
                                .price(87.5f)
                                .cost(78.5f)
                                .build()
                )
                .quantity(10)
                .build();
        lineRepository.save(alreadySavedLine);

        assertThat(lineRepository.findLineByItemId("item1")).isEqualTo(alreadySavedLine);
    }

    @Test
    @DisplayName("Should Not Find Line By Item Id")
    void shouldNotFindLineByItemId() {
        assertThat(lineRepository.findLineByItemId("item3")).isNull();
    }

    @Test
    @DisplayName("Should Delete Lines By Item Ids")
    void shouldDeleteLinesByItemIds() {
        List<Item> alreadySavedItems = List.of(
                Item.builder()
                        .itemId("item1")
                        .itemName("Cool Item 1")
                        .description("Amazing product")
                        .price(87.5f)
                        .cost(78.5f)
                        .build(),
                Item.builder()
                        .itemId("item2")
                        .itemName("Cool Item 2")
                        .description("Amazing product")
                        .price(34.6f)
                        .cost(866.6f)
                        .build()

        );
        itemRepository.saveAll(alreadySavedItems);

        List<Line> alreadySavedLines = List.of(
                Line.builder()
                        .itemId(
                                Item.builder()
                                        .itemId("item1")
                                        .itemName("Cool Item 1")
                                        .description("Amazing product")
                                        .price(87.5f)
                                        .cost(78.5f)
                                        .build()
                        )
                        .quantity(20)
                        .build(),
                Line.builder()
                        .itemId(
                                Item.builder()
                                        .itemId("item2")
                                        .itemName("Cool Item 2")
                                        .description("Amazing product")
                                        .price(34.6f)
                                        .cost(866.6f)
                                        .build()
                        )
                        .quantity(20)
                        .build()
        );
        lineRepository.saveAll(alreadySavedLines);

        // Deleting all Lines
        List<String> itemIds = List.of("item1", "item2");
        lineRepository.deleteLinesWithItemIds(itemIds);

        // Repository should be empty
        assertThat(lineRepository.findAll()).isNotEmpty();

    }
}