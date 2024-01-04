package com.study.design_pattern.decorator.starbucks.component;

public class EspressoCoffee extends BeverageInh {
    @Override
    public double cost() {
        return super.cost() + 0.9;
    }
}
