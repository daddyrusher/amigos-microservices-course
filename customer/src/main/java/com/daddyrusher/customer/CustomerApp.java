package com.daddyrusher.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.daddyrusher.customer",
                "com.daddyrusher.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.daddyrusher.clients"
)
public class CustomerApp {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApp.class, args);
    }
}
