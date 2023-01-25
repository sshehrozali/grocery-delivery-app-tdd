package com.ordering.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {
    private LineRepository lineRepository;

    public List<Line> getAllItemsInInventoryWithQuantity() {
        if (lineRepository.findAll().isEmpty()) {
            throw new RuntimeException("Inventory is empty. No Item found.");
        }
        return lineRepository.findAll();
    }

    public List<Line> saveNewItemsOnlyInInventoryWithQuantity(List<Line> items) {
        if (items.isEmpty()) {
            throw new RuntimeException("Request can't be empty. Please add Items for saving in inventory.");
        }

        items.forEach(line -> {
            if (line == null) {
                throw new RuntimeException("No Item found for saving. Please add Items before saving.");
            }

            if (line.getItemId().getItemId() == null
                    || line.getItemId().getItemName() == null
                    || line.getItemId().getDescription() == null
                    || line.getItemId().getPrice() == null
                    || line.getItemId().getCost() == null
            ) {
                throw new RuntimeException("Values of Items can't be empty. Please check before saving.");
            }
        });
        return lineRepository.saveAll(items);
    }
}
