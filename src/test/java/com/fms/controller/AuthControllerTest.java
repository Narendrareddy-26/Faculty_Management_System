package com.fms.controller;

import com.fms.service.AuthService;
import com.fms.config.JwtAuthenticationFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = AuthController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class
)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    // Mock JWT filter so Spring doesn't try to create real security beans
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Test
    void testRegister() throws Exception {

        when(authService.register(any()))
                .thenReturn("You are registered successfully");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name":"Test User",
                                  "email":"test@gmail.com",
                                  "password":"1234",
                                  "role":"ROLE_HOD",
                                  "ttsNumber":"T1"
                                }
                                """))
                .andExpect(status().isOk());
    }
}