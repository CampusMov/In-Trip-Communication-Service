package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record ChatResource(
        String chatId,
        String carpoolId,
        String driverId,
        String passengerId,
        LocalDateTime createdAt,
        LocalDateTime lastMessageAt,
        String status,
        String lastMessagePreview,
        int unreadCount
) {}

