package com.byakko.service.sales.events;

import com.byakko.infrastructure.kafka.constants.KafkaTopic;
import com.byakko.infrastructure.kafka.consumer.KafkaConsumer;
import com.byakko.service.sales.saga.CreateOrderSaga;
import com.byakko.service.sales.services.OrderService;
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
public class OrderCheckedEventListener implements KafkaConsumer {

    private static final Gson gson = new Gson();

    @Autowired
    private OrderService orderService;

    private final CreateOrderSaga createOrderSaga;

    public OrderCheckedEventListener(ReceiverOptions<String, String> receiverOptions, CreateOrderSaga createOrderSaga) {
        this.createOrderSaga = createOrderSaga;
        KafkaReceiver.create(receiverOptions.subscription(Collections.singleton(KafkaTopic.ORDER_CHECKED_TOPIC)))
                .receive().subscribe(this::handleMessage);
    }

    @Override
    public void handleMessage(ReceiverRecord<String, String> receiverRecord) {
        log.info("Kafka message received: " + receiverRecord.value());

        OrderCheckedEvent orderCheckedEvent = gson.fromJson(receiverRecord.value(), OrderCheckedEvent.class);
        System.out.println(orderCheckedEvent);

        createOrderSaga.checkedOrder(orderCheckedEvent);
    }

}
