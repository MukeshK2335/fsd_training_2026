package com.atbs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CancellationReqDto(
        @NotBlank(message = "Reason for cancellation cannot be blank")
        @Size(max = 255,message = "Message Cannot exceed 255 Character")
        String reason
) {
}
