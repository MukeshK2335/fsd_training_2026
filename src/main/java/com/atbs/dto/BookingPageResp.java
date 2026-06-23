package com.atbs.dto;

import java.util.List;

public record BookingPageResp(
        long totalRecords,
        int totalPages,
        List<BookingRespDto> data
) {
}
