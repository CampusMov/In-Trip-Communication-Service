package com.campusmov.platform.intripcommunicationservice.intripcommunication.interfaces.rest.transform;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.queries.GetChatsByDriverIdQuery;

public class GetChatsByDriverQueryFromResourceAssembler {
    public static GetChatsByDriverIdQuery toQuery(String driverId) {
        return new GetChatsByDriverIdQuery(driverId);
    }
}

