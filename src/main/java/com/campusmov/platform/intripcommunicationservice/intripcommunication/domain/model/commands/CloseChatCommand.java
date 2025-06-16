package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands;

public record CloseChatCommand(String chatId) {
    public CloseChatCommand {
        if (chatId.isBlank()) {
            throw new IllegalArgumentException("chatId cannot be blank");
        }
    }
}
