package com.atbs.dto;

import java.util.List;

public record FlightOwnerPageResp(
        long totalRecords,
        int totalPages,
        List<FlightOwnerAllDto> data
) {
}
