package com.campusmov.platform.intripcommunicationservice.intripcommunication.application.internal.queryservices;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.aggregates.Chat;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.entities.Message;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.queries.*;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.*;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatQueryService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.infrastructure.persistence.jpa.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChatQueryServiceImpl implements ChatQueryService {

    private final ChatRepository chatRepository;

    @Override
    public Optional<Chat> handle(GetChatForPassengerQuery query) {
        var passengerId = new UserId(query.passengerId());
        var carpoolId   = new CarpoolId(query.carpoolId());
        return chatRepository
                .findByCarpoolIdAndPassengerId(carpoolId, passengerId)
                .filter(chat -> chat.getStatus() == ChatStatus.OPEN);
    }

    @Override
    public Collection<Chat> handle(GetChatsByDriverIdQuery query) {
        var driverId = new UserId(query.driverId());
        return chatRepository
                .findAllByDriverIdAndStatus(driverId, ChatStatus.OPEN);
    }

    @Override
    public Collection<Message> handle(GetMessagesByChatIdQuery query) {
        var chatOpt = chatRepository
                .findById(new ChatId(query.chatId()))
                .filter(chat -> chat.getStatus() == ChatStatus.OPEN);

        var chat = chatOpt.orElseThrow(() ->
                new IllegalArgumentException("Open chat with ID " + query.chatId() + " not found")
        );
        return chat.getMessages();
    }

    @Override
    public int handle(GetUnreadCountQuery query) {
        var chatOpt = chatRepository
                .findById(new ChatId(query.chatId()))
                .filter(chat -> chat.getStatus() == ChatStatus.OPEN);

        var chat = chatOpt.orElseThrow(() ->
                new IllegalArgumentException("Open chat with ID " + query.chatId() + " not found")
        );
        return chat.getUnreadCount();
    }
}