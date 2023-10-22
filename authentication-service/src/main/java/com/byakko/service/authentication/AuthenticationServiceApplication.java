package com.byakko.service.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.byakko.service.authentication.dataaccess", "com.byakko.common.dataaccess" })
@EntityScan(basePackages = { "com.byakko.service.authentication.dataaccess", "com.byakko.common.dataaccess" })
@SpringBootApplication(scanBasePackages = "com.byakko")
public class AuthenticationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }
  
}
