package com.atbs.model;

import com.atbs.enums.ScheduleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "departure_time",nullable = false)
    private LocalDateTime departureTime;
    @Column(name = "schedule_date")
    private LocalDate scheduledDate;
    @Column(name = "arrival_time",nullable = false)
    private LocalDateTime arrivalTime;
    @Column(nullable = false)
    private Double fare;
    @Enumerated(EnumType.STRING)
    @Column(name = "schedule_status")
    private ScheduleStatus scheduleStatus;
    @ManyToOne
    private Flight flight;
    @ManyToOne
    private Route route;

}
