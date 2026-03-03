package com.fms.service;

import com.fms.entity.Role;
import com.fms.entity.TeachingLog;
import com.fms.entity.User;
import com.fms.repository.TeachingLogRepository;
import com.fms.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HodService {

    private final UserRepository userRepository;
    private final TeachingLogRepository teachingLogRepository;

    public List<User> getAllFaculties() {
        return userRepository.findByRole(Role.ROLE_FACULTY);
    }

    public String deleteFaculty(Long id) {
        userRepository.deleteById(id);
        return "Faculty deleted successfully";
    }

    public List<TeachingLog> getDailyReport(LocalDate date) {
        return teachingLogRepository.findByDate(date);
    }

    public List<TeachingLog> getWeeklyReport() {

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(7);

        return teachingLogRepository.findByDateBetween(startOfWeek, today);
    }
}