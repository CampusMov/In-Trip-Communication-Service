package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MessageResource(
        String messageId,
        String senderId,
        String content,
        LocalDateTime sentAt,
        String status
) {}

