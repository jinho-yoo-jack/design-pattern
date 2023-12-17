package com.study.reative.application;

import com.study.reative.infrastructure.configuration.KafkaConsumerConfig;
import com.study.reative.infrastructure.kafka.ReactiveKafkaListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
