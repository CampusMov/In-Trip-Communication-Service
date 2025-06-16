package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.queries;

public record GetMessagesByChatIdQuery(String chatId) {
    public GetMessagesByChatIdQuery {
        if (chatId == null || chatId.isBlank()) {
            throw new IllegalArgumentException("chatId cannot be null or empty");
        }
    }
}
