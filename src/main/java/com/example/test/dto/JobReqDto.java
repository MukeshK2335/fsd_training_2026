package com.example.test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JobReqDto(
        @NotBlank
        String title,
        @NotBlank
        String description,
        String location,
        @NotNull
        Double salary
) {
}
