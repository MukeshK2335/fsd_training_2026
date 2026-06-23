package com.atbs.dto;

public record FlightOwnerGetByIdDto(
        int id,
        String userName,
        String name,
        String companyName,
        String contactNumber,
        String address,
        String email
) {
}
