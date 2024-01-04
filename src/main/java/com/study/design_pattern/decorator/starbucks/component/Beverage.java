package com.study.design_pattern.decorator.starbucks.component;

public abstract class Beverage {
    String description = "Not";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
