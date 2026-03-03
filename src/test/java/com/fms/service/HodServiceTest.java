package com.fms.service;

import com.fms.entity.Role;
import com.fms.entity.User;
import com.fms.repository.TeachingLogRepository;
import com.fms.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HodServiceTest {

    @InjectMocks
    private HodService hodService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeachingLogRepository teachingLogRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFaculties() {

        when(userRepository.findByRole(Role.ROLE_FACULTY))
                .thenReturn(List.of(new User()));

        List<User> result = hodService.getAllFaculties();

        assertEquals(1, result.size());
    }
}