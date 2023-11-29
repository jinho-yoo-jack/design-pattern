package com.study.design_pattern.strategy;

import com.study.design_pattern.strategy.dto.Apple;
import com.study.design_pattern.strategy.dto.Color;
import com.study.design_pattern.strategy.predicate.filter.ApplePredicate;
import com.study.design_pattern.strategy.predicate.print.ApplePrintPredicate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Slf4j
public class AppleServiceTest {
    @Autowired
    private AppleService appleService;

    @Test
    public void getFilterPredicateTest() {
        List<Apple> appleList = new ArrayList<>();
        appleList.add(Apple.builder().color(Color.GREEN).weight(15L).build());
        appleList.add(Apple.builder().color(Color.RED).weight(1L).build());
        appleList.add(Apple.builder().color(Color.RED).weight(10L).build());
        List<Apple> result  = appleService.getFilteredAppleList(appleList, "AppleColorPredicate");
    }

}
