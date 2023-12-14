package com.byakko.infrastructure.kafka.producer;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface KafkaProducer {

    Mono<Void> send(String topic, String message);

}
