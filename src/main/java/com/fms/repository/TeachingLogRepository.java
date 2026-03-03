package com.fms.repository;

import com.fms.entity.TeachingLog;
import com.fms.entity.Timetable;
import com.fms.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TeachingLogRepository extends JpaRepository<TeachingLog, Long> {

    // Get logs for a specific timetable
    List<TeachingLog> findByTimetable(Timetable timetable);

    // Get logs by date (used for daily report)
    List<TeachingLog> findByDate(LocalDate date);

    // Get logs between dates (used for weekly report)
    List<TeachingLog> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Get logs by faculty (via timetable relationship)
    List<TeachingLog> findByTimetable_Faculty(User faculty);

    // Get logs by faculty and date
    List<TeachingLog> findByTimetable_FacultyAndDate(User faculty, LocalDate date);
}