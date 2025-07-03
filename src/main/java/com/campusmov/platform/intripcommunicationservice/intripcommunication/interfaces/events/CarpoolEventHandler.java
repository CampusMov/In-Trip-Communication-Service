package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.events;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services.ChatCommandService;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.events.transforms.CreateChatCommandFromEventAssembler;
import com.campusmov.platform.intripcommunicationservice.shared.domain.model.events.LinkedPassengerCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarpoolEventHandler {
    private final ChatCommandService chatCommandService;

    @EventListener
    public void handleLinkedPassengerCreatedEvent(LinkedPassengerCreatedEvent event) {
        var createChatCommand = CreateChatCommandFromEventAssembler.toCommandFromEvent(event);
        chatCommandService.handle(createChatCommand);
    }
}
