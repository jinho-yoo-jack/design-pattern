package com.study.design_pattern.decorator.starbucks.deco;

import com.study.design_pattern.decorator.starbucks.component.Beverage;

public class Moka extends CondimentDecorator {
    public Moka(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.20;
    }

    @Override
    public String getDescription() {
        return "Moka";
    }
}
