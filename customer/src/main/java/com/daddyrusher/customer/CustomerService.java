package com.daddyrusher.customer;

import com.daddyrusher.clients.fraud.FraudCheckResponse;
import com.daddyrusher.clients.fraud.FraudClient;
import com.daddyrusher.clients.notification.NotificationClient;
import com.daddyrusher.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(
        CustomerRepository repository,
        FraudClient fraudClient,
        NotificationClient notificationClient) {

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

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to service!", customer.getFirstName()))
        );
        //todo: add some checks - first name, last name, email
        //todo: store customer in db
        //todo: check if fraudster
        //todo: send notification
    }
}
