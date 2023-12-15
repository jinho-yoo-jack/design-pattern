package com.study.design_pattern.infrastructure;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

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
}
