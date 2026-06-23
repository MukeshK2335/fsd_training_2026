package com.atbs.dto;

import com.atbs.enums.ScheduleStatus;

public record ScheduleStatusCount(
        ScheduleStatus status,
        Long count
) {
}
