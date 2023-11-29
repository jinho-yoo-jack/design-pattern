package com.study.design_pattern.strategy.predicate.filter;

import com.study.design_pattern.strategy.dto.Apple;
import org.springframework.stereotype.Component;

import static com.study.design_pattern.strategy.dto.Color.GREEN;

@Component
public class AppleColorPredicate extends ApplePredicate {
    @Override
    public boolean filter(Apple apple) {
        return GREEN.equals(apple.getColor());
    }
}
