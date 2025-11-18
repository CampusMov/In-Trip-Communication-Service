package com.campusmov.platform.intripcommunicationservice.intripcommunication.infrastructure.brokers.kafka;

import com.campusmov.platform.intripcommunicationservice.shared.domain.model.events.LinkedPassengerCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class RoutingMatchingEventSource {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Bean
    public Consumer<LinkedPassengerCreatedEvent> linkedPassengerCreatedEventConsumer() {
        return applicationEventPublisher::publishEvent;
    }
}
