package com.food.ordering.system.payment.service.domain;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.food.ordering.system.payment.service.dataaccess")
@EntityScan(basePackages = "com.food.ordering.system.payment.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.food.ordering.system",exclude = KafkaAutoConfiguration.class)
public class PaymentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class,args);
    }
}
