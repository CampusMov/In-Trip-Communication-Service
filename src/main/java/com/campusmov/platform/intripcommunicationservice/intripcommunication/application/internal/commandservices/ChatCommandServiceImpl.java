package com.campusmov.platform.intripcommunicationservice.intripcommunication.application.internal.commandservices;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.aggregates.Chat;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.CloseChatCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.CreateChatCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.MarkMessageReadCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.SendMessageCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.entities.Message;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.CarpoolId;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.ChatId;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.ChatStatus;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.UserId;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatCommandService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.infrastructure.persistence.jpa.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
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

    @Override
    public Optional<Message> handle(SendMessageCommand cmd) {
        var chatId = new ChatId(cmd.chatId());
        var chat   = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Chat with ID " + cmd.chatId() + " not found"));

        if (chat.getStatus() != ChatStatus.OPEN) {
            throw new IllegalStateException("Cannot send message to a closed chat");
        }

        var message = chat.sendMessage(cmd);
        chatRepository.save(chat);

        return Optional.of(message);
    }

    @Override
    public void handle(String chatId, MarkMessageReadCommand cmd) {
        var chatObj = new ChatId(chatId);
        var chat    = chatRepository.findById(chatObj)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Chat for message " + cmd.messageId() + " not found"));
        chat.markMessageRead(cmd);
        chatRepository.save(chat);
    }

    @Override
    public void handle(CloseChatCommand cmd) {
        var chatId = new ChatId(cmd.chatId());
        var chat   = chatRepository.findById(chatId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Chat with ID " + cmd.chatId() + " not found"));
        chat.closeChat(cmd);
        chatRepository.save(chat);
    }
}