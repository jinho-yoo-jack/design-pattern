package com.study.design_pattern.decorator.starbucks.deco;

import com.study.design_pattern.decorator.starbucks.component.Beverage;

public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;

    public abstract String getDescription();
}
