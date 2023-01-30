package com.ordering.app.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@RequiredArgsConstructor
class ItemRepositoryTest {

    private ItemRepository itemRepository;

    @Test
    @DisplayName("Should Find Item By Item Id")
    void shouldFindItemByItemId() {
    }
}