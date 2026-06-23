package com.atbs.dto;

public record FlightRespDto(
        int id,
        String flightName,
        String flightNumber,
        int totalSeats,
        Double checkInBaggage,
        Double cabinBaggage
) {
}
