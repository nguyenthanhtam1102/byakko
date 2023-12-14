package com.byakko.service.product.events;

import com.byakko.infrastructure.kafka.constants.KafkaTopic;
import com.byakko.infrastructure.kafka.consumer.KafkaConsumer;
import com.byakko.service.product.saga.CheckOrderSaga;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.Collections;

@Service
@Slf4j
public class OrderCreatedEventListener implements KafkaConsumer {

    private static final Gson gson = new Gson();

    private final CheckOrderSaga checkOrderSaga;

    public OrderCreatedEventListener(ReceiverOptions<String, String> receiverOptions, CheckOrderSaga checkOrderSaga) {
        this.checkOrderSaga = checkOrderSaga;
        KafkaReceiver.create(receiverOptions.subscription(Collections.singleton(KafkaTopic.ORDER_CREATED_TOPIC)))
                .receive()
                .doOnError(throwable -> System.out.println(throwable.getMessage()))
                .subscribe(this::handleMessage);
    }

    @Override
    public void handleMessage(ReceiverRecord<String, String> receiverRecord) {
        log.info("Message Received: " + receiverRecord.value());
        OrderCreatedEvent orderCreatedEvent = gson.fromJson(receiverRecord.value(), OrderCreatedEvent.class);
        checkOrderSaga.checkOrder(orderCreatedEvent);
    }

}
