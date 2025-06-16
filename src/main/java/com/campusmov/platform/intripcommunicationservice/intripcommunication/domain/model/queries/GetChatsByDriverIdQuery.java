package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.queries;

public record GetChatsByDriverIdQuery(String driverId) {
    public GetChatsByDriverIdQuery {
        if (driverId == null || driverId.isBlank()) {
            throw new IllegalArgumentException("driverId cannot be null or blank");
        }
    }
}

