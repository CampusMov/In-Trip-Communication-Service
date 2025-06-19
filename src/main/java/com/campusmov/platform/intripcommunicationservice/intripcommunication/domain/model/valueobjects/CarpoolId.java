package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record CarpoolId(
        String value) {
    public CarpoolId {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("CarpoolId cannot be null or blank");
        }
    }
}
