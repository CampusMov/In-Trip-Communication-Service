package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.websocket.controllers;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatCommandService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.MessageResource;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform.MessageResourceFromEntityAssembler;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.websocket.dto.ChatMessagePayload;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.websocket.transform.SendMessageCommandFromPayloadAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class WSChatsController {
    private final ChatCommandService chatCommandService;

    @MessageMapping("/chat/{chatId}/send")
    @SendTo("/topic/chats/{chatId}")
    public MessageResource sendMessage(
            @DestinationVariable String chatId,
            @Payload ChatMessagePayload chatMessagePayload
    ) {
        var command = SendMessageCommandFromPayloadAssembler.toCommand(chatId, chatMessagePayload);
        var message = chatCommandService.handle(command);
        if (message.isEmpty()) throw new IllegalArgumentException("Message could not be sent, chatId: " + chatId + ", payload: " + chatMessagePayload);
        System.out.println("Message sent successfully, chatId: " + chatId + ", payload: " + chatMessagePayload);
        return MessageResourceFromEntityAssembler.toResource(message.get());
    }
}