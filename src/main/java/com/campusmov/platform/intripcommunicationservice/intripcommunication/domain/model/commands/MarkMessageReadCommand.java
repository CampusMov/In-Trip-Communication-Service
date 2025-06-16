package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands;

import java.time.LocalDateTime;

public record MarkMessageReadCommand(
        String messageId,
        String readerId,
        LocalDateTime readAt
) {
    public MarkMessageReadCommand {
        if (messageId.isBlank() || readerId.isBlank() || readAt == null) {
            throw new IllegalArgumentException("All fields must be provided");
        }
    }
}
