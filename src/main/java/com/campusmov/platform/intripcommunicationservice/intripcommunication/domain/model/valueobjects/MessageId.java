package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record MessageId(
        String value) {
    public MessageId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("MessageId cannot be null or blank");
        }
    }
}
