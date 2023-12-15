package com.study.design_pattern.infrastructure.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
                listener.getClass().getMethod("subscribe", String.class)
                    .invoke(listener, topic);

            } catch (Exception e) {
                log.error("{} Subscribe Failed - Error Message => {}", topic, e.getMessage());
            }
        }));

    }
}
