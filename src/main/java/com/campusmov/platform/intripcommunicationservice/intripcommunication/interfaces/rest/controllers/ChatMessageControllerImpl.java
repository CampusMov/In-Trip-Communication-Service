package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.controllers;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.queries.GetMessagesByChatIdQuery;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatCommandService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatQueryService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.*;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform.*;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.swagger.ChatMessageController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ChatMessageControllerImpl implements ChatMessageController {

    private final ChatQueryService chatQueryService;
    private final ChatCommandService chatCommandService;

    @Override
    public ResponseEntity<Collection<MessageResource>> getMessages(@PathVariable String chatId) {
        var messages = chatQueryService.handle(new GetMessagesByChatIdQuery(chatId));
        var list = messages.stream()
                .map(MessageResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<MessageResource> sendMessage(String chatId, @RequestBody SendMessageResource resource) {
        var command = SendMessageCommandFromResourceAssembler.toCommand(chatId, resource);
        var messageOptional = chatCommandService.handle(command);
        if (messageOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var res = MessageResourceFromEntityAssembler.toResource(messageOptional.get());
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> markMessageRead(
            String chatId, String messageId, @RequestBody MarkMessageReadResource resource) {
        var command = MarkMessageReadCommandFromResourceAssembler.toCommand(messageId, resource);
        chatCommandService.handle(chatId, command);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
