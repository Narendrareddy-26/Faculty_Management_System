package com.fms.service;

import com.fms.entity.TeachingLog;
import com.fms.entity.Timetable;
import com.fms.entity.User;
import com.fms.repository.TeachingLogRepository;
import com.fms.repository.TimetableRepository;
import com.fms.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final UserRepository userRepository;
    private final TimetableRepository timetableRepository;
    private final TeachingLogRepository teachingLogRepository;

    private User getLoggedInFaculty() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
    }

    public List<Timetable> getTimetable() {
        return timetableRepository.findByFaculty(getLoggedInFaculty());
    }

    public List<Timetable> getFreePeriods() {
        return timetableRepository
                .findByFacultyAndIsAssignedFalseAndIsLunchFalse(getLoggedInFaculty());
    }

    public String addTeachingLog(Long timetableId, String topic, String description) {

        Timetable timetable = timetableRepository.findById(timetableId)
                .orElseThrow(() -> new RuntimeException("Timetable not found"));

        if (timetable.isLunch()) {
            throw new RuntimeException("Cannot add log during lunch break");
        }

        TeachingLog log = TeachingLog.builder()
                .timetable(timetable)
                .date(LocalDate.now())
                .topic(topic)
                .description(description)
                .build();

        teachingLogRepository.save(log);

        return "Teaching log added successfully";
    }

    public String updateTeachingLog(Long id, String topic, String description) {

        TeachingLog log = teachingLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Log not found"));

        log.setTopic(topic);
        log.setDescription(description);

        teachingLogRepository.save(log);

        return "Teaching log updated successfully";
    }
}