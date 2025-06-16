package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.SendMessageCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.SendMessageResource;

import java.time.LocalDateTime;

public class SendMessageCommandFromResourceAssembler {
    public static SendMessageCommand toCommand(String chatId, SendMessageResource r) {
        return new SendMessageCommand(chatId, r.senderId(), r.content(), LocalDateTime.now());
    }
}
