package com.study.design_pattern.application;

import com.study.design_pattern.infrastructure.configuration.KafkaConsumerConfig;
import com.study.design_pattern.infrastructure.kafka.ReactiveKafkaListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverRecord;

@Slf4j
@RequiredArgsConstructor
@ReactiveKafkaListener(topic = "test")
public class TradeListener {
    private final KafkaConsumerConfig kafkaConsumerConfig;

    public void subscribe(String topicName) {
        kafkaConsumerConfig.kafkaReceiver(topicName).receive()
            .subscribe(stringStringReceiverRecord -> {
                log.info("Received Records => {}", stringStringReceiverRecord);
            });
    }
}
