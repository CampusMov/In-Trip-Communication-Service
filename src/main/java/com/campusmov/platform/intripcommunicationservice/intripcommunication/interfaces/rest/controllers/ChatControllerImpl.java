package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.controllers;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatCommandService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.ChatResource;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.CreateChatResource;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform.*;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.swagger.ChatController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatControllerImpl implements ChatController {

    private final ChatCommandService chatCommandService;

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

}

