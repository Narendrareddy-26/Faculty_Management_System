package com.fms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "timetable")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private User faculty;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private int periodNumber;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean isLunch;

    private boolean isAssigned;
}