package com.example.test.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public record ApplicationRespDto(
        int id,
        Instant appliedAt,
        String jobTitle,
        String companyName
) {
}
