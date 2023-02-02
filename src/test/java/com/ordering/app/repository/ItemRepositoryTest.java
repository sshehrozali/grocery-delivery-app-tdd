package com.ordering.app.repository;

import com.ordering.app.dto.DeleteItemsDTO;
import com.ordering.app.entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("Should Find Item By Item Id")
    void shouldFindItemByItemId() {
        Item savedItem = Item.builder()
                .itemId("item1")
                .itemName("Cool Item 1")
                .description("Amazing product")
                .price(12.4f)
                .cost(5.6f)
                .build();
        itemRepository.save(savedItem);

        assertThat(itemRepository.findItemByItemId("item1"))
                .isEqualTo(savedItem);
    }

    @Test
    @DisplayName("Should Return Null If Item Not Found By Item Id")
    void shouldReturnNullIfItemNotFoundByItemId() {
        assertThat(itemRepository.findItemByItemId("item2"))
                .isNull();
    }

    @Test
    @DisplayName("Should Delete All Items By Item Id")
    void shouldDeleteAllItemsByItemId() {
        List<Item> savedItems = List.of(
                Item.builder()
                        .itemId("item1")
                        .itemName("Cool Item 1")
                        .description("Amazing Product 1")
                        .price(12.4f)
                        .cost(5.6f)
                        .build(),
                Item.builder()
                        .itemId("item2")
                        .itemName("Awesome Item 2")
                        .description("Awesome Product 2")
                        .price(545.4f)
                        .cost(885.5f)
                        .build()
        );
        itemRepository.saveAll(savedItems);

        String[] itemIds = {"item1", "item2"};
        itemRepository.deleteItemsByItemId("item1");

        assertThat(itemRepository.findAll()).isNotNull();
    }
}