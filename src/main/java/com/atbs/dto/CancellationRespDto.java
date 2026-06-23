package com.atbs.dto;




import java.time.LocalDate;
import java.time.LocalDateTime;

public record CancellationRespDto(
        int cancellationId,
        LocalDateTime cancellationDate,
        String reason,
        double refundAmount,
        String refundStatus,
        String  cancellationStatus,
        int bookingId,
        LocalDate bookingDate,
        String bookingStatus,
        String passengerName,
        String flightNumber,
        String flightName,
        String origin,
        String destination,
        LocalDate scheduledDate
) {
}
