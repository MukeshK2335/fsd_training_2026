package com.atbs.dto;

import java.time.LocalDateTime;
import java.util.List;

public record BookingRespDto(
        int bookingId,

        String bookedBy,

        String flightNumber,

        String flightName,

        String origin,

        String destination,

        LocalDateTime departureTime,

        LocalDateTime arrivalTime,

        List<String> passengerNames,

        List<String> seatNumbers,

        Double totalAmount,

        String bookingStatus,

        Double checkInBaggage,

        Double cabinBaggage
) {
}
