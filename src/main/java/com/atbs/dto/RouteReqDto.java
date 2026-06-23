package com.atbs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RouteReqDto(
        @NotNull
        @NotBlank
        String origin,
        @NotNull
        @NotBlank
        String destination
) {
}
