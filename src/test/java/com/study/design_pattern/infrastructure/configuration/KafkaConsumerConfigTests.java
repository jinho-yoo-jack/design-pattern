package com.study.design_pattern.infrastructure.configuration;

import com.study.reative.infrastructure.configuration.KafkaConsumerConfig;
import com.study.reative.infrastructure.kafka.ReactiveKafkaListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import reactor.kafka.receiver.KafkaReceiver;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class KafkaConsumerConfigTests {
    @Autowired
    private KafkaConsumerConfig kafkaConsumerConfig;

    @Autowired
    private ApplicationContext context;

    @Test
    public void kafkaPropertiesTest() {
        KafkaReceiver<String, String> receiver = kafkaConsumerConfig.kafkaReceiver("test");
    }

    @Test
    public void getBeans() {
        context.getBeansWithAnnotation(ReactiveKafkaListener.class).forEach(((beanName, listener) -> {
            ReactiveKafkaListener annotation = Optional.ofNullable(listener.getClass().getAnnotation(ReactiveKafkaListener.class))
                .orElseThrow(NullPointerException::new);
            String topic = annotation.topic();
            String groupId = annotation.groupId();
            try {
                listener.getClass().getMethod("subscribe", String.class, String.class)
                    .invoke(listener, topic, groupId);

            } catch (Exception e) {
                log.error("{} Subscribe Failed - Error Message => {}", topic, e.getMessage());
            }
        }));

    }
}
