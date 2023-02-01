package com.ordering.app;

import com.ordering.app.entity.Item;
import com.ordering.app.entity.Line;
import com.ordering.app.repository.ItemRepository;
import com.ordering.app.repository.LineRepository;
import com.ordering.app.service.StaffService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StaffServiceTest {
    @InjectMocks
    private StaffService serviceUnderTest;
    @Mock
    private LineRepository lineRepository;
    @Mock
    private ItemRepository itemRepository;

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

    @Test
    @DisplayName("Should Throw Exception If No Items Available In Inventory")
     void shouldThrowExceptionIfNoItemAvailableInInventory() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    serviceUnderTest.getAllItemsInInventoryWithQuantity();
                }
        );
     }

     @Test
     @DisplayName("Should Save New Items Only In Inventory With Quantity")
     void shouldSaveNewItemsOnlyInInventoryWithQuantity() {
         List<Line> itemsToSave = new ArrayList<>();
         itemsToSave.add(
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
                         .quantity(100)
                         .build()
         );
        when(lineRepository.saveAll(any())).thenReturn(itemsToSave);

        assertThat(serviceUnderTest.saveNewItemsOnlyInInventoryWithQuantity(itemsToSave))
                .isEqualTo(itemsToSave);
     }

     @Test
     @DisplayName("Should Throw Exception If Request Is Null For Saving New Items In Inventory")
     void shouldThrowExceptionIfRequestIsNullForSavingNewItemsInInventory() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    serviceUnderTest.saveNewItemsOnlyInInventoryWithQuantity(null);
                }
        );
     }

     @Test
     @DisplayName("Should Throw Exception If Any Item Value is Null While Saving Items In Inventory")
     void shouldThrowExceptionIfAnyItemValueIsNullWhileSavingItemsInInventory() {
        ArrayList<Line> itemsToSave = new ArrayList<>();
        itemsToSave.add(
                Line.builder()
                        .itemId(
                                Item.builder()
                                        .itemId("item1")
                                        .itemName("Cool Item 1")
                                        .description("Amazing product")
                                        .price(87.5f)
                                        // not providing cost value here (null)
                                        .build()
                        )
                        .quantity(100)
                        .build()
        );

        assertThrows(
                RuntimeException.class,
                () -> {
                    serviceUnderTest.saveNewItemsOnlyInInventoryWithQuantity(itemsToSave);
                }
        );
     }

     @Test
     @DisplayName("Should Throw Exception If Quantity Value Is Null While Saving New Items In Inventory")
     void shouldThrowExceptionIfQuantityValueIsNullWhileSavingNewItemsInInventory() {
         ArrayList<Line> itemsToSave = new ArrayList<>();
         itemsToSave.add(
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
                         .build()
         );

        assertThrows(
                RejectedExecutionException.class,
                () -> {
                    serviceUnderTest.saveNewItemsOnlyInInventoryWithQuantity(itemsToSave);
                }
        );
     }

     @Test
     @DisplayName("Should Only Update Items Available In Inventory")
     void shouldOnlyUpdateItemsAvailableInInventory() {
         Item alreadyExistsItem = Item.builder()
                 .itemId("item1")
                 .itemName("Cool Item 1")
                 .description("Amazing product")
                 .price(87.5f)
                 .cost(78.5f)
                 .build();
         Line alreadyExistsLine = Line.builder()
                 .itemId(alreadyExistsItem)
                 .quantity(89)
                 .build();
         // Stub behaviour when we are checking if item already exists
         when(itemRepository.findItemByItemId(anyString())).thenReturn(alreadyExistsItem);
         when(lineRepository.findLineByItemId(anyString())).thenReturn(alreadyExistsLine);

         // Items to save/update
         ArrayList<Line> itemsToUpdate = new ArrayList<>();
         itemsToUpdate.add(
                 Line.builder()
                         .itemId(
                                 // This item should be updated
                                 Item.builder()
                                         .itemId("item1")   // Passing same itemId
                                         .itemName("Updated Cool Item 1") // But different itemName
                                         .description("Updated Amazing product") // But different description
                                         .price(67.5f)  // But different price
                                         .cost(100.7f)   // And different cost as well
                                         .build()
                         )
                         .quantity(10)
                         .build()
         );

         // Calling serviceUnderTest to save/update items
         serviceUnderTest.saveNewItemsOnlyInInventoryWithQuantity(itemsToUpdate);

         // Asserting that did item get updated?
         assertThat(itemRepository.findItemByItemId("item1")).isEqualTo(itemsToUpdate.get(0).getItemId());
     }
}
