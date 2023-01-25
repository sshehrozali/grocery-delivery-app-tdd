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
        return lineRepository.saveAll(items);
    }
}
