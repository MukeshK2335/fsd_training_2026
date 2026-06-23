        package com.atbs.dto;

        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.NotNull;
        import jakarta.validation.constraints.Size;

        public record PassengerReqDto(
                @NotBlank
                @NotNull
                String name,
                @NotBlank
                @NotNull
                String gender,
                @NotBlank
                @NotNull
                @Size(max = 10,message = "Phone number should be 10 digits")
                String contact_number,
                @NotBlank
                @NotNull
                @Size(max = 50,message = "Address should contain max 50 characters")
                String address,
                @NotBlank
                @NotNull
                String email,
                @NotBlank
                @NotNull
                @Size(min = 4,message = "The username should be least 4 character")
                String username,
                @NotBlank
                @NotNull
                String password
        ) {
        }
