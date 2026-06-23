package com.atbs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FlightOwnerReqDto(
        @NotBlank
        @NotNull
        @Size(min = 4,message = "The Company name should be least 4 character")
        String companyName,
        @NotBlank
        @NotNull
        @Size(max = 10,message = "Phone number should be 10 digits")
        String contactNumber,
        @NotBlank
        @NotNull
        @Size(max = 50,message = "Address should contain max 50 characters")
        String address,
        @NotBlank
        @NotNull
        String contactEmail,
        @NotBlank
        @NotNull
        @Size(min = 4,message = "The username should be least 4 character")
        String username

) {
}
