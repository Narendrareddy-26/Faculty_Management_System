package com.fms.service;

import com.fms.entity.Timetable;
import com.fms.entity.User;
import com.fms.repository.TeachingLogRepository;
import com.fms.repository.TimetableRepository;
import com.fms.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacultyServiceTest {

    @InjectMocks
    private FacultyService facultyService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TimetableRepository timetableRepository;

    @Mock
    private TeachingLogRepository teachingLogRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("test@gmail.com", null)
        );
    }

    @Test
    void testGetTimetable() {

        User user = new User();
        user.setEmail("test@gmail.com");

        when(userRepository.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));

        when(timetableRepository.findByFaculty(user))
                .thenReturn(List.of(new Timetable()));

        List<Timetable> result = facultyService.getTimetable();

        assertFalse(result.isEmpty());
    }
}