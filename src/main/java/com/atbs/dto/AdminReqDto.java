package com.atbs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AdminReqDto(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @NotBlank
        @Size(max = 10,message = "Phone number should be 10 digits")
        String contact_number,
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        @Size(min = 4,message = "The username should be least 4 character")
        String username

) {
}
