package com.atbs.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record PaymentRespDto(
        int paymentId,
        String transactionId,
        Double amount,
        String paymentMethod,
        String paymentStatus,
        Instant paymentTime,
        int bookingId,
        LocalDate bookingDate,
        String bookingStatus,
        String passengerName,
        String flightName,
        String flightNumber,
        String origin,
        String destination,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime
) {
}
