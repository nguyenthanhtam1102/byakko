package com.byakko.service.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.byakko.service.sales", "com.byakko.common" })
@EntityScan(basePackages = { "com.byakko.service.sales", "com.byakko.common" })
@SpringBootApplication(scanBasePackages = "com.byakko")
public class SalesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesServiceApplication.class, args);
    }
  
}
