package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.swagger;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "/chats", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Chats", description = "Endpoints for managing in-trip communication chats")
public interface ChatController {

    @PostMapping
    @Operation(
            summary = "Create a new chat",
            description = "Starts a chat for a given passenger on a specific carpool ride"
    )
    @ApiResponse(responseCode = "201", description = "Chat successfully created")
    @ApiResponse(responseCode = "400", description = "A chat already exists for that passenger and ride")
    ResponseEntity<ChatResource> createChat(@RequestBody CreateChatResource resource);

    @PostMapping("/{chatId}/close")
    @Operation(
            summary = "Close a chat",
            description = "Marks the specified chat as CLOSED"
    )
    @ApiResponse(responseCode = "200", description = "Chat successfully closed")
    @ApiResponse(responseCode = "404", description = "Chat not found")
    ResponseEntity<Void> closeChat(@PathVariable String chatId);
}

