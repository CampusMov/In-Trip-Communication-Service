package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.websocket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessagePayload {
    private String senderId;
    private String content;
}