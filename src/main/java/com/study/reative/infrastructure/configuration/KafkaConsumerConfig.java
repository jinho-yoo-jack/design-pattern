package com.study.reative.infrastructure.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.*;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerConfig {
    private final KafkaProperties kafkaProperties;

    public KafkaReceiver<String, String> kafkaReceiver(String topicName) {
        ReceiverOptions<String, String> options = ReceiverOptions.<String, String>create(
                kafkaProperties.buildConsumerProperties(null)
            )
            .subscription(Set.of(topicName))
            .addAssignListener((consumer) -> log.info("onPartitionsAssigned {}", consumer))
            .addRevokeListener((consumer) -> log.info("onPartitionsRevoked {}", consumer));

        return KafkaReceiver.create(options);
    }
}
