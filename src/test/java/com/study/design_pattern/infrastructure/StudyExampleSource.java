package com.study.design_pattern.infrastructure;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class StudyExampleSource {
    @Test
    public void backpressureExample1Test() {
        Flux.range(1, 5)
            .doOnRequest(data -> log.info("$ doOnRequest: {}", data))
            .subscribe(new BaseSubscriber<Integer>() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    log.info("# hookOnSubscribe: {}", subscription);
                    request(1);
                }

                @SneakyThrows
                @Override
                protected void hookOnNext(Integer value) {
                    Thread.sleep(2000L);
                    log.info("# hookOnNext: {}", value);
                    request(1);
                }
            });
    }

    @Test
    public void reactorContextExampleATest() {
        Mono
            .deferContextual(contextView ->
                Mono.just("Hello" + " " + contextView.get("firstName"))
                    .doOnNext(data -> log.info("# just doOnNext : {}", data))
            )
            .subscribeOn(Schedulers.boundedElastic())
            .publishOn(Schedulers.parallel())
            .transformDeferredContextual((mono, contextView) -> mono.map(data -> data + " " + contextView.get("lastName")))
            .contextWrite(context -> context.put("lastName", "jobs"))
            .contextWrite(context -> context.put("firstName", "Steve"))
            .subscribe(data -> log.info("# onNext: {}", data));
    }

    @Test
    public void reactorDebuggingExampleTest() {
        Map<String, String> fruits = new HashMap<>();
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
        fruits.put("melon", "멜론");
        Flux<String> fruitGroups = Flux
            .fromArray(new String[]{"BANANAS", "APPLES", "PEARS", "MELONS"})
            .doOnRequest(request -> log.info("# doOnRequest -> {}", request))
//            .doOnNext(fruit -> log.info("# doOnNext -> {}", fruit))
//            .doOnSubscribe(data -> log.info("# doOnSubscribe -> {}", data))
//            .subscribeOn(Schedulers.boundedElastic())
            .map(String::toLowerCase)
            .map(fruit -> fruit.substring(0, fruit.length() - 1)) // 복수에서 단수로
            .map(fruits::get)
            .map(translated -> "맛있는 " + translated);
//            .publishOn(Schedulers.parallel());

        log.info("# Start Subscribe");
//        fruitGroups
//            .subscribe(
//                data -> {
//                    log.info("# subscribe -> {}", data);
//                },
//                error -> log.error(" # onError: ", error),
//                () -> log.info("# onComplete")
//            );


        fruitGroups
            .subscribe(
                new BaseSubscriber<String>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        subscription.request(2);
                    }

                    @Override
                    protected void hookOnNext(String value) {
                        request(1);
                        log.info("BaseSubscriber# Value -> {}", value);
                    }
                }
            );
    }

    @Test
    public void calculated() {
        log.info("Result -> {}", 8 / 0);
    }

    @Test
    public void reactorDebuggingExample2Test() {
        Flux
            .just(2, 4, 6, 8)
            .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> {
                log.info("x -> {} y -> {} x/y -> {}", x, y, x / y);
                return x / y;
            })
            .map(num -> num + 2)
            .subscribe(
                data -> log.info("Data : {}", data),
                err -> log.error("Error : ", err)
            );
    }

    @Test
    public void reactorTestToolExampleTest() {
        StepVerifier
            .create(Mono.just("Hello Reactor"))  // Sequence 생성
            .expectNext("Hello Reactor")            // emit된 데이터 기댓값 평가
            .expectComplete()                         // onComplete 시그널 기댓값 평가
            .verify();
    }

    public static Flux<Tuple2<String, Integer>> getCOVID19Count(Flux<Long> source) {
        return source
            .flatMap(notUse -> Flux.just(
                Tuples.of("서울", 10),
                Tuples.of("경기", 5),
                Tuples.of("강원", 3),
                Tuples.of("충청", 6),
                Tuples.of("경상", 5),
                Tuples.of("전라", 8)
            ));
    }

    public static Flux<String> getCapitalizedCountry(Flux<String> source) {
        return source
            .map(country -> country.substring(0, 1).toUpperCase() + country.substring(1));
    }

    @Test
    public void getCapitalizedCountryTest() {
        StepVerifier
            .create(
                getCapitalizedCountry(Flux.just("korea", "england", "canada", "india"))
            )
            .expectSubscription()
            .recordWith(ArrayList::new)
            .expectComplete()
            .verify();
    }

    @Test
    public void backpressureTest() {
        Flux request = Flux.range(1, 50)
            .doOnRequest(subscription -> log.info("# doOnRequest -> {}", subscription));

        request.subscribe(
            new BaseSubscriber() {
                @Override
                protected void hookOnSubscribe(Subscription subscription) {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Requesting the next 10 elements!!!");
                        subscription.request(10);
                    }                }

                @Override
                protected void hookOnNext(Object value) {
                    log.info("# onNext -> {}", value);
                }

                @Override
                protected void hookOnComplete() {
                    log.info("# onComplete");
                }

                @Override
                protected void hookOnError(Throwable throwable) {
                    log.error("# onError -> {}", throwable.getMessage());
                }
            }
        );
    }

}
