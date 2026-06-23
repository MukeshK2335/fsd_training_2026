package com.atbs.dto;

import java.util.List;

public record SchedulePageResp(
        long totalRecords,
        int totalPages,
        List<ScheduleRespDto> data
) {
}
