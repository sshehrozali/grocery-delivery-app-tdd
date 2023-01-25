package com.ordering.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {
    private LineRepository lineRepository;

    public List<Line> getAllItemsInInventoryWithQuantity() {
        return lineRepository.findAll();
    }
}
