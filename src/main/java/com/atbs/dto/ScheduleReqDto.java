package com.atbs.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleReqDto(

        int flightId,
        int routeId,
        double fare,
        @NotNull(message = "Arrival time cannot be null")
        LocalDateTime arrivalTime,
        @NotNull(message = "Departure time cannot be null")
        LocalDateTime departureTime,
        @NotNull(message = "Schedule date cannot be null")
        LocalDate scheduleDate

) {
}
