package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.websocket.publisher;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.entities.Message;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.MessageResource;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform.MessageResourceFromEntityAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatWebSocketPublisherImpl implements ChatWebSocketPublisher {

    private final SimpMessagingTemplate messaging;

    @Override
    public void publishNewMessage(String chatId, Message message) {
        MessageResource resource = MessageResourceFromEntityAssembler.toResource(message);
        String destination = "/topic/chats/" + chatId;
        messaging.convertAndSend(destination, resource);
    }
}