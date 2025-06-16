package com.campusmov.platform.intripcommunicationservice.intripcommunication.application.internal.commandservices;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.aggregates.Chat;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.*;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.*;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatCommandService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.infrastructure.persistence.jpa.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChatCommandServiceImpl implements ChatCommandService {

    private final ChatRepository chatRepository;

    @Override
    public Optional<Chat> handle(CreateChatCommand cmd) {
        var carpoolId   = new CarpoolId(cmd.carpoolId());
        var passengerId = new UserId(cmd.passengerId());
        if (chatRepository.existsByCarpoolIdAndPassengerId(carpoolId, passengerId)) {
            return Optional.empty();
        }
        var chat = Chat.from(cmd);
        return Optional.of(chatRepository.save(chat));
    }
}

