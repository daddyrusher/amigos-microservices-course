package com.daddyrusher.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.daddyrusher.notification",
                "com.daddyrusher.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.daddyrusher.clients"
)
public class NotificationApp {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApp.class, args);
    }
}
