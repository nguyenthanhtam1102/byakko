package com.byakko.service.sales.service.authentication.application.utils;

import com.byakko.service.sales.service.authentication.domain.domainapplication.utils.KafkaMessageProducer;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Condition;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaMessageImpl implements KafkaMessageProducer {
    private final KafkaTemplate<String, Condition> kafkaTemplate;

    public KafkaMessageImpl(KafkaTemplate<String, Condition> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void CDCKafka(Condition condition) {
        kafkaTemplate.send(condition.getTypeQuery(),condition);
        System.out.println("Sent message: " + condition.getContent());
    }
}
