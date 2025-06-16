package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.CreateChatCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.CreateChatResource;

public class CreateChatCommandFromResourceAssembler {
    public static CreateChatCommand toCommand(CreateChatResource createChatResource) {
        return new CreateChatCommand(createChatResource.carpoolId(), createChatResource.driverId(), createChatResource.passengerId());
    }
}
