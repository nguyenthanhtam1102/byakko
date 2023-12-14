package com.byakko.service.product.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScans({
        @ComponentScan("com.byakko.service.product"),
        @ComponentScan("com.byakko.infrastructure.kafka")
})
public class AppConfig {
}
