        package com.atbs.dto;

        import jakarta.validation.constraints.NotEmpty;
        import jakarta.validation.constraints.NotNull;

        import java.util.List;

        public record BookingReqDto(
                @NotNull
                Integer scheduleId,
                @NotEmpty
                List<String> passengerNames,
                @NotEmpty
                List<Integer> ages,
                @NotEmpty
                List<Integer> seatIds


        ) {
        }
