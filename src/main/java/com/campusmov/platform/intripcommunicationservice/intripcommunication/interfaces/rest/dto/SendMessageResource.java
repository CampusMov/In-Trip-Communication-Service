package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto;

import lombok.Builder;

@Builder
public record SendMessageResource(
        String senderId,
        String content
) {}
