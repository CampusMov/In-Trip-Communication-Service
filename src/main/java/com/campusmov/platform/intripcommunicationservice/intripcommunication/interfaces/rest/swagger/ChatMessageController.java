package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.swagger;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "/chats/{chatId}/messages", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Chat Messages", description = "Endpoints for sending and retrieving in-trip messages")
public interface ChatMessageController {
    @PostMapping
    @Operation(
            summary = "Send a message",
            description = "Sends a new message in the specified chat"
    )
    @ApiResponse(responseCode = "201", description = "Message created")
    @ApiResponse(responseCode = "404", description = "Chat not found or is closed")
    ResponseEntity<MessageResource> sendMessage(
            @PathVariable String chatId,
            @RequestBody SendMessageResource resource
    );

    @PostMapping("/{messageId}/read")
    @Operation(
            summary = "Mark message as read",
            description = "Marks the specified message in the chat as READ"
    )
    @ApiResponse(responseCode = "200", description = "Message marked as read")
    @ApiResponse(responseCode = "404", description = "Chat or message not found")
    ResponseEntity<Void> markMessageRead(
            @PathVariable String chatId,
            @PathVariable String messageId,
            @RequestBody MarkMessageReadResource resource
    );
}