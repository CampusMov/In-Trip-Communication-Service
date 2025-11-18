package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.events.transforms;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.CreateChatCommand;
import com.campusmov.platform.intripcommunicationservice.shared.domain.model.events.LinkedPassengerCreatedEvent;

public class CreateChatCommandFromEventAssembler {
    public static CreateChatCommand toCommandFromEvent(LinkedPassengerCreatedEvent event) {
        return CreateChatCommand.builder()
                .carpoolId(event.getCarpoolId())
                .driverId(event.getDriverId())
                .passengerId(event.getPassengerId())
                .build();
    }
}
