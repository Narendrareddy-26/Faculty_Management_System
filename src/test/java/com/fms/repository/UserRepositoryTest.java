package com.fms.repository;

import com.fms.entity.Role;
import com.fms.entity.User;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {

        User user = User.builder()
                .name("Test")
                .email("repo@gmail.com")
                .password("pass")
                .role(Role.ROLE_HOD)
                .ttsNumber("T1")
                .build();

        userRepository.save(user);

        Optional<User> found =
                userRepository.findByEmail("repo@gmail.com");

        assertTrue(found.isPresent());
    }
}