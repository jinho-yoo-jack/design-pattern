package com.study.design_pattern.decorator.starbucks.deco;

import com.study.design_pattern.decorator.starbucks.component.EspressoCoffee;

public class MokaCoffee extends EspressoCoffee {
    @Override
    public double cost() {
        return super.cost() + 10.0;
    }
}
