package com.atbs.dto;

import com.atbs.model.Route;

import java.util.List;

public record RoutePageResp(
        long totalRecords,
        int totalPages,
        List<Route> data
) {
}
