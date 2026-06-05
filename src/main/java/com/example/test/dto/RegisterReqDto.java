package com.example.test.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterReqDto(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String role,
        @NotBlank
        String name,
        String resumeSummary,
        String companyName
) {
}
