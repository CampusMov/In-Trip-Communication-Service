package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.queries;

public record GetUnreadCountQuery(String chatId, String userId) {
    public GetUnreadCountQuery {
        if (chatId.isBlank() || userId.isBlank()) {
            throw new IllegalArgumentException("Both chatId and userId are required");
        }
    }
}
