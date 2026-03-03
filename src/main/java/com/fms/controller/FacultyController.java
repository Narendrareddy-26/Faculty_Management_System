package com.fms.controller;

import com.fms.entity.Timetable;
import com.fms.service.FacultyService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
@RequiredArgsConstructor
@CrossOrigin
public class FacultyController {

    private final FacultyService facultyService;

    // View weekly timetable
    @GetMapping("/timetable")
    public ResponseEntity<List<Timetable>> getTimetable() {
        return ResponseEntity.ok(facultyService.getTimetable());
    }

    // View free periods
    @GetMapping("/free-periods")
    public ResponseEntity<List<Timetable>> getFreePeriods() {
        return ResponseEntity.ok(facultyService.getFreePeriods());
    }

    // Add teaching log
    @PostMapping("/log")
    public ResponseEntity<?> addTeachingLog(
            @RequestParam Long timetableId,
            @RequestParam String topic,
            @RequestParam String description) {

        return ResponseEntity.ok(
                facultyService.addTeachingLog(timetableId, topic, description)
        );
    }

    // Update teaching log
    @PutMapping("/log/{id}")
    public ResponseEntity<?> updateTeachingLog(
            @PathVariable Long id,
            @RequestParam String topic,
            @RequestParam String description) {

        return ResponseEntity.ok(
                facultyService.updateTeachingLog(id, topic, description)
        );
    }
}