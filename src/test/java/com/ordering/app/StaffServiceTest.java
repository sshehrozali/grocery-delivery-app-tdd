package com.ordering.app;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StaffServiceTest {
    @InjectMocks
    private StaffService staffService;
    @Mock
    private LineRepository lineRepository;
}
