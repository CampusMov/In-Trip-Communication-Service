package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.aggregates.Chat;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.ChatResource;

public class ChatResourceFromEntityAssembler {
    public static ChatResource toResource(Chat chat) {
        return ChatResource.builder()
                .chatId(chat.getId().value())
                .carpoolId(chat.getCarpoolId().value())
                .driverId(chat.getDriverId().value())
                .passengerId(chat.getPassengerId().value())
                .createdAt(chat.getCreatedAt())
                .lastMessageAt(chat.getLastMessageAt())
                .status(chat.getStatus().name())
                .lastMessagePreview(chat.getLastMessagePreview())
                .unreadCount(chat.getUnreadCount())
                .build();
    }
}
