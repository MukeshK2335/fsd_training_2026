package com.atbs.dto;

import com.atbs.enums.ScheduleStatus;

public record LabelCountDto(
        ScheduleStatus label,
        Long count
) {
}
