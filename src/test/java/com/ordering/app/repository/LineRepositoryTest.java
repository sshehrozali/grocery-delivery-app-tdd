package com.ordering.app.repository;

import com.ordering.app.entity.Item;
import com.ordering.app.entity.Line;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LineRepositoryTest {

    @Autowired
    private LineRepository lineRepository;

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
}