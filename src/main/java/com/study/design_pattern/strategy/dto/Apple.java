package com.study.design_pattern.strategy.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Apple {
    private Color color;
    private Long weight;
}
