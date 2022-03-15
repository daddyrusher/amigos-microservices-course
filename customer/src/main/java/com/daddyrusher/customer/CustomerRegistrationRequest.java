package com.daddyrusher.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
