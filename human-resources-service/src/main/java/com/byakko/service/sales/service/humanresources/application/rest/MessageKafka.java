package com.byakko.service.sales.service.humanresources.application.rest;

import com.byakko.service.sales.service.humanresources.application.utils.KafkaConsumerService;

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
