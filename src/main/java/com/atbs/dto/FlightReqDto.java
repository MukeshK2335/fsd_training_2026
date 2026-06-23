package com.atbs.dto;

import jakarta.validation.constraints.NotNull;

public record FlightReqDto(

        @NotNull(message = "This field is Mandatory.It should not be null")
        String flightName,
        @NotNull
        String flightNumber,
        @NotNull
        int totalSeats,
        @NotNull
        Double checkInBaggage,
        @NotNull
        Double cabinBaggage
) {

}
