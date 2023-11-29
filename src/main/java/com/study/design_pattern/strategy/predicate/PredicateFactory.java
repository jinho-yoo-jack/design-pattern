package com.study.design_pattern.strategy.predicate;

import com.study.design_pattern.strategy.predicate.filter.ApplePredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class PredicateFactory {
    private Map<String, ApplePredicate> predicate;

    @Autowired
    public PredicateFactory(Set<ApplePredicate> predicateSet) {
        createPredicate(predicateSet);
    }

    private void createPredicate(Set<ApplePredicate> predicateSet) {
        predicate = new HashMap<String, ApplePredicate>();
        predicateSet.forEach(p -> predicate.put(p.getFilterName(), p));
    }

    public ApplePredicate findBy(String className){
        return predicate.get(className);
    }

}
