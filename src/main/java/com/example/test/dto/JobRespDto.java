package com.example.test.dto;

public record JobRespDto(
        int id,
        String title,
        String location,
        double salary,
        String companyName
) {
}
