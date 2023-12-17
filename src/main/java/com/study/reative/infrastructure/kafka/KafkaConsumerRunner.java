package com.study.reative.infrastructure.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerRunner implements ApplicationRunner {

    private final ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        applicationContext.getBeansWithAnnotation(ReactiveKafkaListener.class).forEach(((beanName, listener) -> {
            ReactiveKafkaListener annotation = Optional.ofNullable(listener.getClass().getAnnotation(ReactiveKafkaListener.class))
                .orElseThrow(NullPointerException::new);
            String topic = annotation.topic();
            log.info("bean:{} class-path:{} topic-name:{}", beanName, listener, topic);
            try {
                // getMethod(method_name, argument_type, ...);
                listener.getClass().getMethod("subscribe", String.class)
                    .invoke(listener, topic); // invoke(Instance, Parameters_1, Parameters_2, ...);

            } catch (Exception e) {
                log.error("{} Subscribe Failed - Error Message => {}", topic, e.getMessage());
            }
        }));

    }
}
