package com.study.design_pattern.decorator.starbucks.component;

public class HouseBlend extends Beverage {
    public HouseBlend(){
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
