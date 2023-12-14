package com.byakko.infrastructure.kafka.producer;

import com.byakko.infrastructure.kafka.exceptions.KafkaProducerException;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Component
public class KafkaProducerImpl implements KafkaProducer {

    @Autowired
    private KafkaSender<String, String> sender;

    @Override
    public Mono<Void> send(String topic, String message) {
        return sender.send(Mono.just(SenderRecord.create(new ProducerRecord<>(topic, message), message)))
                .then()
                .onErrorMap(throwable -> new KafkaProducerException("Send message to kafka error: " + throwable.getMessage()));
    }

}
