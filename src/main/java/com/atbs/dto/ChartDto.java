package com.atbs.dto;

import java.util.List;

public record ChartDto(
        String title,
        List<String> labels,
        List<Long> data
) {
}
