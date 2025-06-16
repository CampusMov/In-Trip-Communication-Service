package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.queries.GetChatForPassengerQuery;

public class GetChatForPassengerQueryFromResourceAssembler {
    public static GetChatForPassengerQuery toQuery(String passengerId, String carpoolId) {
        return new GetChatForPassengerQuery(passengerId, carpoolId);
    }
}
