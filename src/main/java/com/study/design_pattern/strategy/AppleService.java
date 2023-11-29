package com.study.design_pattern.strategy;

import com.study.design_pattern.strategy.dto.Apple;
import com.study.design_pattern.strategy.predicate.PredicateFactory;
import com.study.design_pattern.strategy.predicate.filter.ApplePredicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppleService {
    private final PredicateFactory factory;

    public List<Apple> getFilteredAppleList(List<Apple> appleList, String className) {
        List<Apple> result = new ArrayList<Apple>();
        ApplePredicate predicate = factory.findBy(className);
        for (Apple apple : appleList) {
            if (predicate.filter(apple))
                result.add(apple);
        }
        return result;
    }
}
