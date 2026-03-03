package com.fms.service;

import com.fms.config.JwtService;
import com.fms.dto.AuthRequest;
import com.fms.entity.Role;
import com.fms.entity.User;
import com.fms.repository.SubjectRepository;
import com.fms.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
        import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess() {

        AuthRequest request = AuthRequest.builder()
                .name("Test User")
                .email("test@gmail.com")
                .password("1234")
                .role(Role.ROLE_HOD)
                .ttsNumber("TTS1")
                .build();

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userRepository.existsByTtsNumber(request.getTtsNumber())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("encodedPass");

        String result = authService.register(request);

        assertEquals("You are registered successfully", result);
        verify(userRepository, times(1)).save(any(User.class));
    }
}