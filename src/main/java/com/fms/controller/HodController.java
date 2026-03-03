package com.fms.controller;

import com.fms.entity.TeachingLog;
import com.fms.entity.User;
import com.fms.service.HodService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hod")
@RequiredArgsConstructor
@CrossOrigin
public class HodController {

    private final HodService hodService;

    // View all faculty
    @GetMapping("/faculties")
    public ResponseEntity<List<User>> getAllFaculties() {
        return ResponseEntity.ok(hodService.getAllFaculties());
    }

    // Delete faculty
    @DeleteMapping("/faculty/{id}")
    public ResponseEntity<?> deleteFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(hodService.deleteFaculty(id));
    }

    // Daily report
    @GetMapping("/reports/daily")
    public ResponseEntity<List<TeachingLog>> getDailyReport(
            @RequestParam String date) {

        return ResponseEntity.ok(
                hodService.getDailyReport(LocalDate.parse(date))
        );
    }

    // Weekly report
    @GetMapping("/reports/weekly")
    public ResponseEntity<List<TeachingLog>> getWeeklyReport() {
        return ResponseEntity.ok(hodService.getWeeklyReport());
    }
}