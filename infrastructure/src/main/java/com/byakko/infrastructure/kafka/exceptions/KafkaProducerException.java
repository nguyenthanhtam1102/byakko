package com.byakko.infrastructure.kafka.exceptions;

public class KafkaProducerException extends RuntimeException {

    public KafkaProducerException(String message) {
        super(message);
    }

}
