package com.study.design_pattern.strategy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class Apple {
    private final Color color;
    private final Long weight;
}
