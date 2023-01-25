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
    @Mock
    private OfferRepository offerRepository;

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
        assertThat(serviceUnderTest.getAllItems()).isEqualTo(lines);
    }

    @Test
    @DisplayName("Should Throw Exception If No Offers Are Available for Any Items")
    void shouldThrowExceptionIfNoOffersFoundForAnyItems() {
        assertThrows(
                RuntimeException.class,
                () -> {
                    serviceUnderTest.getAllAvailableOffersForEachItems();
                }
        );
    }

    @Test
    @DisplayName("Should Get Available Offers for Each Item")
    void shouldGetAvailableOffersForEachItem() {
        ArrayList<Offer> offers = new ArrayList<>();
        offers.add(
                Offer.builder()
                        .itemId(
                                Item.builder()
                                        .itemId("item1")
                                        .itemName("Cool item 1")
                                        .price("200")
                                        .build()
                        )
                        .offerId("offer1")
                        .offerName("July Offer")
                        .description("Buy 1 Get 1 Free")
                        .priceReduction(5.6f)
                        .quantityThreshold(10)
                        .build()
        );
        when(offerRepository.findAll()).thenReturn(offers);

        assertThat(serviceUnderTest.getAllAvailableOffersForEachItems()).isEqualTo(offers);
    }

}
