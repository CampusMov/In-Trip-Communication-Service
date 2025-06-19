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

    @GetMapping("/passenger/{passengerId}/ride/{carpoolId}")
    @Operation(
            summary = "Get passenger’s chat",
            description = "Retrieves the open chat for the specified passenger and ride"
    )
    @ApiResponse(responseCode = "200", description = "Chat found and returned")
    @ApiResponse(responseCode = "404", description = "No open chat found for that passenger and ride")
    ResponseEntity<ChatResource> getChatForPassenger(
            @PathVariable String passengerId,
            @PathVariable String carpoolId
    );

    @GetMapping("/driver/{driverId}")
    @Operation(
            summary = "List driver’s chats",
            description = "Lists all open chats for the specified driver"
    )
    @ApiResponse(responseCode = "200", description = "List of open chats for driver returned")
    ResponseEntity<Collection<ChatResource>> getChatsByDriver(@PathVariable String driverId);

    @PostMapping("/{chatId}/close")
    @Operation(
            summary = "Close a chat",
            description = "Marks the specified chat as CLOSED"
    )
    @ApiResponse(responseCode = "200", description = "Chat successfully closed")
    @ApiResponse(responseCode = "404", description = "Chat not found")
    ResponseEntity<Void> closeChat(@PathVariable String chatId);

    @GetMapping("/{chatId}/unread-count")
    @Operation(
            summary = "Get unread message count",
            description = "Retrieves the count of unread messages for the specified chat and user"
    )
    @ApiResponse(responseCode = "200", description = "Unread message count successfully retrieved")
    @ApiResponse(responseCode = "404", description = "Chat not found")
    ResponseEntity<Integer> getUnreadCount(@PathVariable String chatId, @RequestParam String userId);
}

