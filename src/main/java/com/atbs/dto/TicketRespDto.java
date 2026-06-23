package com.atbs.dto;

import java.time.LocalDateTime;
import java.util.List;

public record TicketRespDto(

    int bookingId,

    String flightNumber,

    String flightName,

    String bookedBy,

    String origin,

    String destination,

    LocalDateTime departureTime,

    LocalDateTime arrivalTime,

    List<String> passengerNames,

    List<String> seatNumbers,


    Double totalAmount,

    Double checkInBaggage,

    Double cabinBaggage,

    String bookingStatus

    ){}


