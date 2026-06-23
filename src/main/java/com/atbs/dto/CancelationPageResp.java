package com.atbs.dto;



import java.util.List;

public record CancelationPageResp(
        long totalRecords,
        int totalPages,
        List<CancellationRespDto> data
) {
}
