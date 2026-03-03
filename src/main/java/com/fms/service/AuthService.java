package com.fms.service;

import com.fms.config.JwtService;
import com.fms.dto.AuthRequest;
import com.fms.dto.AuthResponse;
import com.fms.entity.Role;
import com.fms.entity.Subject;
import com.fms.entity.User;
import com.fms.repository.SubjectRepository;
import com.fms.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(AuthRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByTtsNumber(request.getTtsNumber())) {
            throw new RuntimeException("TTS Number already exists");
        }

        Subject subject = null;

        if (request.getRole() == Role.ROLE_FACULTY) {

            if (request.getSubjectName() == null) {
                throw new RuntimeException("Faculty must have one subject");
            }

            subject = subjectRepository.findByName(request.getSubjectName())
                    .orElseGet(() ->
                            subjectRepository.save(
                                    Subject.builder()
                                            .name(request.getSubjectName())
                                            .build()
                            )
                    );
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .ttsNumber(request.getTtsNumber())
                .subject(subject)
                .build();

        userRepository.save(user);

        return "You are registered successfully";
    }

    public AuthResponse login(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .message("Login Successful")
                .token(token)
                .role(user.getRole().name())
                .email(user.getEmail())
                .build();
    }

    public String forgotPassword(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("Email not registered");
        }

        // In real app -> send email verification link
        return "Password reset link sent to email (Simulation)";
    }

    public String resetPassword(String email, String newPassword) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return "Password reset successful";
    }
}