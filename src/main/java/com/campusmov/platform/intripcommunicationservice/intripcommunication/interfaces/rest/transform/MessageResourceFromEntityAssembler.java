package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.entities.Message;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.MessageResource;

public class MessageResourceFromEntityAssembler {
    public static MessageResource toResource(Message message) {
        return MessageResource.builder()
                .messageId(message.getId().value())
                .senderId(message.getSenderId().value())
                .content(message.getContent())
                .sentAt(message.getSentAt())
                .status(message.getStatus().name())
                .build();
    }
}
