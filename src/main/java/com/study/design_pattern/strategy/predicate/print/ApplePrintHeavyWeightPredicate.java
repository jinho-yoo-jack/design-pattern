package com.study.design_pattern.strategy.predicate.print;

import com.study.design_pattern.strategy.dto.Apple;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplePrintHeavyWeightPredicate implements ApplePrintPredicate {
    @Override
    public void print(Apple apple) {
        StringBuffer printBody = new StringBuffer();
        printBody.append("\n===== Apple INFO =====\n")
            .append("\tColor: ").append(apple.getColor()).append("\n")
            .append("\tWeight: ").append(apple.getWeight()).append("\n");

        log.info(printBody.toString());
    }
}
