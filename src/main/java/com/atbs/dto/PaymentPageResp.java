package com.atbs.dto;

import java.util.List;

public record PaymentPageResp(
        long totalRecords,
        int totalPages,
        List<PaymentRespDto> data
) {
}
