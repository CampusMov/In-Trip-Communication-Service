package com.campusmov.platform.intripcommunicationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InTripCommunicationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InTripCommunicationServiceApplication.class, args);
    }

}
