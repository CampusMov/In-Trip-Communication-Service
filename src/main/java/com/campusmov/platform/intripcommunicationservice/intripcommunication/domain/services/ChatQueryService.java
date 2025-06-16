package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.services;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.aggregates.Chat;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.queries.*;

import java.util.Collection;
import java.util.Optional;

public interface ChatQueryService {
    Optional<Chat> handle(GetChatForPassengerQuery query);
    Collection<Chat> handle(GetChatsByDriverIdQuery query);
}

