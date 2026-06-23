package com.atbs.dto;

import java.util.List;

public record PassengerPageResp(
        long totalRecords,
        int totalPages,
        List<PassengerAllDto> data
) {
}
