package com.ordering.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ItemRepository itemRepository;
    private final LineRepository lineRepository;
    private final OfferRepository offerRepository;

    public List<LineDTO> getAllItems() {
        if (lineRepository.findAll().isEmpty()) {
            throw new RuntimeException("No Items available in Stock yet. Come back later.");
        }
        return lineRepository.findAll()
                .stream()
                .map(
                        line -> LineDTO.builder()
                                .itemId(
                                        ItemDTO.builder()
                                                .id(line.getItemId().getItemId())
                                                .name(line.getItemId().getItemName())
                                                .description(line.getItemId().getDescription())
                                                .price(line.getItemId().getPrice())
                                                .build()
                                )
                                .quantity(line.getQuantity())
                                .build()
                ).collect(Collectors.toList());
    }

    public List<Offer> getAllAvailableOffersForEachItems() {
        if (offerRepository.findAll().isEmpty()) {
            throw new RuntimeException("No Available Offers found for any Item yet. Bad luck :(");
        }
        return offerRepository.findAll();
    }
}
