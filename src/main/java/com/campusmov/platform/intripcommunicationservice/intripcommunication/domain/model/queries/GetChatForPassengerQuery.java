package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.queries;

public record GetChatForPassengerQuery(String passengerId, String carpoolId) {
    public GetChatForPassengerQuery {
        if (passengerId == null || passengerId.isBlank()
                || carpoolId  == null || carpoolId.isBlank()) {
            throw new IllegalArgumentException("passengerId and carpoolId are required");
        }
    }
}

