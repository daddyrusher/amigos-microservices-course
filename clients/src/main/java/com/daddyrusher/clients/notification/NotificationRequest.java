package com.daddyrusher.clients.notification;

public record NotificationRequest(
        Long id,
        String email,
        String message) {
}
