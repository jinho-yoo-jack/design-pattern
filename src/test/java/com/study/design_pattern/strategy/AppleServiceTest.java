package com.study.design_pattern.strategy;

import com.study.design_pattern.strategy.dto.Apple;
import com.study.design_pattern.strategy.dto.Color;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

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
        List<Apple> result = appleService.getFilteredAppleList(appleList, "AppleColorPredicate");
    }

    public void changeWeight(Apple a) {
        a.setWeight(1234L);
    }

    @Test
    public void callByRefTest() {
        Apple a = Apple.builder()
            .color(Color.RED)
            .weight(100L)
            .build();

        changeWeight(a);
        log.info("Apple Weight => {}", a.getWeight());
    }

    @Test
    public void UpStreamDownStreamExample_1() {
        Flux
            .just(1, 2, 3, 4, 5, 6)
            .filter(input -> input % 2 == 0) // Signature (T) -> boolean
            .map(filteredInput -> filteredInput * 2)
            .subscribe(System.out::println);
    }

}
