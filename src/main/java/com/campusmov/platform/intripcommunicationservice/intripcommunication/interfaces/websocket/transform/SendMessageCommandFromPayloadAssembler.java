package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.websocket.transform;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.SendMessageCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.websocket.dto.ChatMessagePayload;

import java.time.LocalDateTime;

public class SendMessageCommandFromPayloadAssembler {
    public static SendMessageCommand toCommand(String chatId, ChatMessagePayload payload) {
        return new SendMessageCommand(
                chatId,
                payload.getSenderId(),
                payload.getContent(),
                LocalDateTime.now()
        );
    }
}
