package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.websocket.publisher;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.entities.Message;

public interface ChatWebSocketPublisher {
    void publishNewMessage(String chatId, Message message);
}
