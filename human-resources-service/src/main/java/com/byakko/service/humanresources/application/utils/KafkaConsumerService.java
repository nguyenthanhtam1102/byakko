package com.byakko.service.humanresources.application.utils;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
        @KafkaListener(topics = "INSERT", groupId = "your-group-id")
        public void receiveMessageInsert(Object message) {
                // Xử lý dữ liệu nhận được từ Kafka
                System.out.println("Received message from Kafka: "+message);
        }
        @KafkaListener(topics = "DELETE", groupId = "your-group-id")
        public void receiveMessageDelete(Object message) {
            // Xử lý dữ liệu nhận được từ Kafka
            System.out.println("Received message from Kafka: "+message);
        }
        @KafkaListener(topics = "UPDATE", groupId = "your-group-id")
        public void receiveMessageUpdate(Object message) {
            // Xử lý dữ liệu nhận được từ Kafka
            System.out.println("Received message from Kafka: "+message);
        }
}
