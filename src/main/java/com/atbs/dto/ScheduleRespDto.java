package com.atbs.dto;

import java.time.LocalDateTime;

public record ScheduleRespDto(
        int id,
        String origin,
        String destination,
        LocalDateTime arrivalTime,
        LocalDateTime departureTime,
        String flightName,
        String flightNumber,
        int totalSeats,
        Double checkInBaggage,
        Double cabinBaggage,
        String airline,
        Double fare,
        String scheduleStatus

) {
}
