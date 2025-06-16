package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(
        String value) {
    public UserId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("UserId cannot be null or blank");
        }
    }
}
