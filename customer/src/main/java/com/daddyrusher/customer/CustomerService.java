package com.daddyrusher.customer;

import com.daddyrusher.amqp.RabbitMQMessageProducer;
import com.daddyrusher.clients.fraud.FraudCheckResponse;
import com.daddyrusher.clients.fraud.FraudClient;
import com.daddyrusher.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
        CustomerRepository repository,
        FraudClient fraudClient,
        RabbitMQMessageProducer messageProducer) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        repository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to service!", customer.getFirstName()));
        messageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
        //todo: add some checks - first name, last name, email
        //todo: store customer in db
        //todo: check if fraudster
        //todo: send notification
    }
}
