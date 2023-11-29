package com.study.design_pattern.strategy.predicate.filter;

import com.study.design_pattern.strategy.dto.Apple;
import org.springframework.stereotype.Component;

@Component
public class AppleHeavyWeightPredicate extends ApplePredicate {
    @Override
    public boolean filter(Apple apple) {
        return apple.getWeight() > 15;
    }
}
