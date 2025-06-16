package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.entities;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.MarkMessageReadCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.SendMessageCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.MessageId;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.MessageStatus;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.UserId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Message {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "message_id", nullable = false, unique = true))
    private MessageId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "sender_id", nullable = false))
    private UserId senderId;

    private String content;
    private LocalDateTime sentAt;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "message_id")
    private List<ReadReceipt> readReceipts = new ArrayList<>();

    protected Message() { /* JPA */ }
}

