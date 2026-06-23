package com.atbs.dto;

public record PassengerAllDto(
        int id,
        String userName,
        String name,
        String gender,
        String contactNumber,
        String address,
        String email
) {
}
