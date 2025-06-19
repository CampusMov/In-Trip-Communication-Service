package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MarkMessageReadResource(
        String readerId,
        LocalDateTime readAt
) {}

