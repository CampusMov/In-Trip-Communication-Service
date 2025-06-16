package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto;

import lombok.Builder;

@Builder
public record CreateChatResource(
        String carpoolId,
        String driverId,
        String passengerId
) {}

