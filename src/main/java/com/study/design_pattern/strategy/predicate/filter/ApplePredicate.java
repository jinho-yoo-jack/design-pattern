package com.study.design_pattern.strategy.predicate.filter;

import com.study.design_pattern.strategy.dto.Apple;

public abstract class ApplePredicate {
    public abstract boolean filter(Apple apple);
    public String getFilterName(){
        return getClass().getSimpleName();
    };
}
