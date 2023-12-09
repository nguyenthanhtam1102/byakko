package com.byakko.service.sales.service.authentication.domain.domainapplication.utils;

import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Condition;
import org.springframework.stereotype.Component;

@Component
public interface KafkaMessageProducer {
    void CDCKafka(Condition condition);

}
