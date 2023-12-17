package com.study.reative.infrastructure.kafka;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ReactiveKafkaListener {
    String topic() default "nhn-kcp";

    String groupId() default "1";

    int partitionSize() default 1;
}
