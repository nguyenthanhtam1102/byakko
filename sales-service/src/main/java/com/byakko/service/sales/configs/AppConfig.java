package com.byakko.service.sales.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ComponentScans({
        @ComponentScan("com.byakko.service.sales"),
        @ComponentScan("com.byakko.infrastructure.kafka")
})
public class AppConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

}
