package com.byakko.service.humanresources.application.rest;

import com.byakko.service.humanresources.application.utils.KafkaConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
public class MessageKafka {
        private final KafkaConsumerService kafkaConsumerService;

        public MessageKafka(KafkaConsumerService kafkaConsumerService) {
            this.kafkaConsumerService = kafkaConsumerService;
        }
        public String receiveMessage(String message) {
            // Gọi phương thức nhận dữ liệu từ Kafka Consumer Service
            kafkaConsumerService.receiveMessageInsert(message);
            return "Receiving messages from Kafka...";
        }
}
