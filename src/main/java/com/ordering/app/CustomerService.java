package com.ordering.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ItemRepository itemRepository;
    private final LineRepository lineRepository;
    private final OfferRepository offerRepository;

    public List<Line> getAllItems() {
        if (lineRepository.findAll().isEmpty()) {
            throw new RuntimeException("No Items available in Stock yet. Come back later.");
        }
        return lineRepository.findAll();
    }

    public List<Offer> getAllAvailableOffersForEachItems() {
        if (offerRepository.findAll().isEmpty()) {
            throw new RuntimeException("No Available Offers found for any Item yet. Bad luck :(");
        }
        return offerRepository.findAll();
    }
}
