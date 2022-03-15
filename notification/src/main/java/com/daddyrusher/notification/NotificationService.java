package com.daddyrusher.notification;

import com.daddyrusher.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository repository) {
    public void send(NotificationRequest notificationRequest) {
        repository.save(
                Notification
                        .builder()
                        .toCustomerId(notificationRequest.id())
                        .toCustomerEmail(notificationRequest.email())
                        .sender("Daddyrusher")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}
