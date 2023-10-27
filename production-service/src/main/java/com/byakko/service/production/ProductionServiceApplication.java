package com.byakko.service.production;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.byakko.service.production.dataaccess", "com.byakko.common.dataaccess" })
@EntityScan(basePackages = { "com.byakko.service.production.dataaccess", "com.byakko.common.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.byakko")
public class ProductionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductionServiceApplication.class, args);
    }
  
}
