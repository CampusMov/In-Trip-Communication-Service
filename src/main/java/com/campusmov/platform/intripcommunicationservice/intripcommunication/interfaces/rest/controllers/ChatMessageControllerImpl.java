package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.controllers;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatCommandService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.*;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform.*;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.swagger.ChatMessageController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatMessageControllerImpl implements ChatMessageController {

    private final ChatCommandService chatCommandService;

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
