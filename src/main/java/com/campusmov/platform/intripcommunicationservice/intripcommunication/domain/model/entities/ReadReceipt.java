package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.entities;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.MarkMessageReadCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.MessageId;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.UserId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ReadReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "message_id", nullable = false))
    private MessageId messageId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "reader_id", nullable = false))
    private UserId readerId;

    private LocalDateTime readAt;

    protected ReadReceipt() { /* JPA */ }
}
