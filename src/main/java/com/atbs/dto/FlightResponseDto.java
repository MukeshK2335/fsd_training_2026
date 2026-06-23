    package com.atbs.dto;

    import com.atbs.model.Flight;

    import java.util.List;

    public record FlightResponseDto(
            long totalRecords,
            int totalPages,
            List<Flight> data
    ) {

    }
