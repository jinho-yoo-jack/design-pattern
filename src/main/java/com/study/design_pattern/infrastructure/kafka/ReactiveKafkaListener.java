package com.study.design_pattern.infrastructure.kafka;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ReactiveKafkaListener {
    String topic() default "nhn-kcp";

    String groupId() default "1";

    int partitionSize() default 1;
}
