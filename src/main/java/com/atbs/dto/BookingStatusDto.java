package com.atbs.dto;

import com.atbs.enums.BookingStatus;

public record BookingStatusDto(
        BookingStatus label,
        Long count

) {
}
