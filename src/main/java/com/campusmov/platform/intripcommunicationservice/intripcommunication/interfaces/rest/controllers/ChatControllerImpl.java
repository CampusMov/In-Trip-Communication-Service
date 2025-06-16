package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.controllers;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.*;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.queries.GetUnreadCountQuery;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatCommandService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatQueryService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.ChatResource;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.CreateChatResource;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform.*;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.swagger.ChatController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ChatControllerImpl implements ChatController {

    private final ChatCommandService chatCommandService;
    private final ChatQueryService chatQueryService;

    @Override
    public ResponseEntity<ChatResource> createChat(CreateChatResource resource) {
        var command = CreateChatCommandFromResourceAssembler.toCommand(resource);
        var chatOptional = chatCommandService.handle(command);
        if (chatOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var res = ChatResourceFromEntityAssembler.toResource(chatOptional.get());
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ChatResource> getChatForPassenger(String passengerId, String carpoolId) {
        var query = GetChatForPassengerQueryFromResourceAssembler.toQuery(passengerId, carpoolId);
        var chatOptional = chatQueryService.handle(query);
        return chatOptional
                .map(chat -> new ResponseEntity<>(ChatResourceFromEntityAssembler.toResource(chat), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Collection<ChatResource>> getChatsByDriver(String driverId) {
        var query = GetChatsByDriverQueryFromResourceAssembler.toQuery(driverId);
        var list = chatQueryService.handle(query).stream()
                .map(ChatResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> closeChat(String chatId) {
        chatCommandService.handle(new CloseChatCommand(chatId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

