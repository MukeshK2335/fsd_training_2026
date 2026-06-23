package com.atbs.dto;

public record FlightOwnerAllDto(
        int id,
        String userName,
        String name,
        String companyName,
        String contactNumber,
        String address,
        String email
) {
}
