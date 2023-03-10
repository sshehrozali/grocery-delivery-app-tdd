package com.ordering.app.service;

import com.ordering.app.dto.DeleteItemsDTO;
import com.ordering.app.entity.Item;
import com.ordering.app.entity.Line;
import com.ordering.app.repository.ItemRepository;
import com.ordering.app.repository.LineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class StaffService {
    private LineRepository lineRepository;
    private ItemRepository itemRepository;

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

            if (line.getQuantity() == null) {
                throw new RejectedExecutionException("Quantity of Item can't be null. Please provide quantity of each item as well before saving.");
            }

            if (line.getItemId().getItemId() == null
                    || line.getItemId().getItemName() == null
                    || line.getItemId().getDescription() == null
                    || line.getItemId().getPrice() == null
                    || line.getItemId().getCost() == null
            ) {
                throw new RuntimeException("Values of Items can't be empty. Please check before saving.");
            }

            if (itemRepository.findItemByItemId(line.getItemId().getItemId()) != null) {
                Item existingItem = itemRepository.findItemByItemId(line.getItemId().getItemId());
                existingItem.setItemName(line.getItemId().getItemName());
                existingItem.setDescription(line.getItemId().getDescription());
                existingItem.setCost(line.getItemId().getCost());
                existingItem.setPrice(line.getItemId().getPrice());
                itemRepository.save(existingItem);    // Updates item entity

                Line existingLine = lineRepository.findLineByItemId(existingItem.getItemId());
                existingLine.setQuantity(line.getQuantity());
                lineRepository.save(existingLine);  // Updates line entity
            }
        });

        return lineRepository.saveAll(items);
    }

    public DeleteItemsDTO deleteItemsByIds(DeleteItemsDTO deleteItemsDTO) {
        return deleteItemsDTO;
    }
}
