package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.websocket.controllers;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.SendMessageCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatCommandService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.websocket.dto.SendPayload;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.websocket.publisher.ChatWebSocketPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class ChatWebSocketController {

    private final ChatCommandService chatCommandService;
    private final ChatWebSocketPublisher publisher;

    @MessageMapping("/chat/{chatId}/send")
    public void handleSendMessage(
            @DestinationVariable String chatId,
            @Payload SendPayload payload
    ) {
        SendMessageCommand cmd;
        try {
            LocalDateTime sentAtDateTime = LocalDateTime.parse(payload.getSentAt());

            cmd = new SendMessageCommand(
                    chatId,
                    payload.getSenderId(),
                    payload.getContent(),
                    sentAtDateTime
            );
        } catch (Exception e) {
            System.err.println("Error parsing sentAt: " + payload.getSentAt() + " - " + e.getMessage());
            return;
        }

        chatCommandService.handle(cmd)
                .ifPresent(msg -> publisher.publishNewMessage(chatId, msg));
    }
}