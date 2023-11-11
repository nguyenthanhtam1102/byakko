package com.byakko.service.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.byakko.service.product", "com.byakko.common" })
@EntityScan(basePackages = { "com.byakko.service.product", "com.byakko.common" })
@SpringBootApplication(scanBasePackages = "com.byakko")
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
  
}
