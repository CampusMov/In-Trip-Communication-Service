package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Embeddable
public record ChatId(
        @GeneratedValue(strategy = GenerationType.UUID)
        String value) {
    public ChatId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("ChatId cannot be null or blank");
        }
    }
}
