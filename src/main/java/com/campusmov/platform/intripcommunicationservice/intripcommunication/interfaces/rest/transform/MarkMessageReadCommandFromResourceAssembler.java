package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.MarkMessageReadCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.dto.MarkMessageReadResource;

public class MarkMessageReadCommandFromResourceAssembler {
    public static MarkMessageReadCommand toCommand(String messageId, MarkMessageReadResource markMessageReadResource) {
        return new MarkMessageReadCommand(messageId, markMessageReadResource.readerId(), markMessageReadResource.readAt());
    }
}
