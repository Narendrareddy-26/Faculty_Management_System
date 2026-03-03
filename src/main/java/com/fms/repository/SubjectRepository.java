package com.fms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    // Find subject by name
    Optional<Subject> findByName(String name);

    // Check if subject already exists
    boolean existsByName(String name);
}