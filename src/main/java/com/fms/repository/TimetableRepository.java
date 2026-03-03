package com.fms.repository;

import com.fms.entity.Timetable;
import com.fms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    // Get full timetable for a faculty
    List<Timetable> findByFaculty(User faculty);

    // Get timetable by faculty and day
    List<Timetable> findByFacultyAndDayOfWeek(User faculty, DayOfWeek dayOfWeek);

    // Get free periods for a faculty
    List<Timetable> findByFacultyAndIsAssignedFalseAndIsLunchFalse(User faculty);

    // Get timetable excluding lunch
    List<Timetable> findByFacultyAndIsLunchFalse(User faculty);
}