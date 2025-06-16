package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.aggregates.Chat;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.entities.Message;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.*;

import java.util.Optional;

public interface ChatCommandService {
    Optional<Chat> handle(CreateChatCommand command);
    Optional<Message> handle(SendMessageCommand command);
    void handle(String chatId, MarkMessageReadCommand command);
}

