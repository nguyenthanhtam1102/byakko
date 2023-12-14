package com.byakko.infrastructure.kafka.consumer;

import org.springframework.stereotype.Service;
import reactor.kafka.receiver.ReceiverRecord;

@Service
public interface KafkaConsumer {

    void handleMessage(ReceiverRecord<String, String> receiverRecord);

}
