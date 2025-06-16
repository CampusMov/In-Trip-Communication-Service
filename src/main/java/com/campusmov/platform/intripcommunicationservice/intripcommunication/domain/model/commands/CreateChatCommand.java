package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands;

public record CreateChatCommand(
        String carpoolId,
        String driverId,
        String passengerId
) {
    public CreateChatCommand {
        if (carpoolId.isBlank() || driverId.isBlank() || passengerId.isBlank()) {
            throw new IllegalArgumentException("All fields must be provided");
        }
    }
}