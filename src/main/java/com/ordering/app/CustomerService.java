package com.ordering.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final ItemRepository itemRepository;
    private final LineRepository lineRepository;

    public List<Item> getAllItems() {
        return
    }
}
