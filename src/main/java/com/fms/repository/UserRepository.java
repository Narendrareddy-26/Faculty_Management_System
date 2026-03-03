package com.fms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.Role;
import com.fms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email (used for login & JWT authentication)
    Optional<User> findByEmail(String email);

    // Check if email already exists (for registration validation)
    boolean existsByEmail(String email);

    // Check if TTS number already exists
    boolean existsByTtsNumber(String ttsNumber);

    // Find all users by role (Used in HOD dashboard)
    java.util.List<User> findByRole(Role role);
}