package com.byakko.infrastructure.kafka.configs;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Value("${kafka.sasl.username}")
    private String SASL_USERNAME;

    @Value("${kafka.sasl_password}")
    private String SASL_PASSWORD;

    @Value("${kafka.bootstrap.servers}")
    private String BOOTSTRAP_SERVERS;

    @Value("${kafka.security.protocol}")
    private String SECURITY_PROTOCOL;

    @Value("${kafka.sasl.mechanism}")
    private String SASL_MECHANISM;

    @Value("${kafka.ssl.truststore.type}")
    private String SSL_TRUSTSTORE_TYPE;

    @Value("${kafka.ssl.truststore.location}")
    private String SSL_TRUSTSTORE_LOCATION;

    @Value("${kafka.ssl.truststore.password}")
    private String SSL_TRUSTSTORE_PASSWORD;

    @Value("${kafka.group.id}")
    private String GROUP_ID;

    @Bean
    public KafkaSender<String, String> kafkaSender() {
        SenderOptions<String, String> senderOptions = SenderOptions.create(getKafkaProperties());
        return KafkaSender.create(senderOptions);
    }

    @Bean
    public ReceiverOptions<String, String> receiverOptions() {
        return ReceiverOptions.create(getKafkaProperties());
    }

    @Bean
    public Properties getKafkaProperties() {
        String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        String jaasConfig = String.format(jaasTemplate, SASL_USERNAME, SASL_PASSWORD);

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", BOOTSTRAP_SERVERS);
        properties.setProperty("security.protocol", SECURITY_PROTOCOL);
        properties.setProperty("sasl.mechanism", SASL_MECHANISM);
        properties.setProperty("sasl.jaas.config", jaasConfig);
        properties.setProperty("ssl.endpoint.identification.algorithm", "");
        properties.setProperty("ssl.truststore.type", SSL_TRUSTSTORE_TYPE);
        properties.setProperty("ssl.truststore.location", SSL_TRUSTSTORE_LOCATION);
        properties.setProperty("ssl.truststore.password", SSL_TRUSTSTORE_PASSWORD);
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", GROUP_ID);

        return properties;
    }

}
