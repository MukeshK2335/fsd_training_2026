package com.atbs.dto;

import java.util.List;

public record CombineStatDto(
        List<String> label,
        List<Long> count
) {
}
