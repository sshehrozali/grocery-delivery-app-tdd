package com.ordering.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    private CustomerService customerService;

    @Test
    @DisplayName("Should Get All Items")
    void shouldGetAllItems() {
        assertThat(customerService.getAllItems()).isNotNull();
    }
}
