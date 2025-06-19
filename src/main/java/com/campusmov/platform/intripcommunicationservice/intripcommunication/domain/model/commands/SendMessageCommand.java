package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands;

import java.time.LocalDateTime;

public record SendMessageCommand(
        String chatId,
        String senderId,
        String content,
        LocalDateTime sentAt
) {
    public SendMessageCommand {
        if (chatId.isBlank() || senderId.isBlank() || content.isBlank() || sentAt == null) {
            throw new IllegalArgumentException("All fields must be provided");
        }
    }
}
