package com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.aggregates;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.CloseChatCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.CreateChatCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.MarkMessageReadCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.commands.SendMessageCommand;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.entities.Message;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.CarpoolId;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.ChatId;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.ChatStatus;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.UserId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Aggregate root representing an in-trip chat session between a passenger and a driver.
 * <p>
 * A {@code Chat} models the communication channel within an ongoing Carpool, allowing users
 * to exchange messages, update read status, and close the session. It follows Domain-Driven
 * Design (DDD) principles and extends {@link AbstractAggregateRoot} to support domain events.
 * <p>
 * The chat contains metadata such as timestamps, unread message counters, last message preview,
 * and the current chat status (OPEN or CLOSED).
 */
@Entity
@Getter
@Setter
public class Chat extends AbstractAggregateRoot<Chat> {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "chat_id", nullable = false, unique = true))
    private ChatId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "carpool_id", nullable = false))
    private CarpoolId carpoolId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "driver_id", nullable = false))
    private UserId driverId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "passenger_id", nullable = false))
    private UserId passengerId;

    private LocalDateTime createdAt;
    private LocalDateTime lastMessageAt;

    @Enumerated(EnumType.STRING)
    private ChatStatus status;

    private String lastMessagePreview;
    private int unreadCount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chat_id")
    private List<Message> messages = new ArrayList<>();

    /**
     * Protected constructor for JPA.
     */
    protected Chat() { /* JPA */ }

    private Chat(CreateChatCommand cmd) {
        this.carpoolId = new CarpoolId(cmd.carpoolId());
        this.driverId = new UserId(cmd.driverId());
        this.passengerId = new UserId(cmd.passengerId());
        this.createdAt = LocalDateTime.now();
        this.lastMessageAt = this.createdAt;
        this.status = ChatStatus.OPEN;
        this.lastMessagePreview = "";
        this.unreadCount = 0;
    }

    public static Chat from(CreateChatCommand cmd) {
        return new Chat(cmd);
    }

    public Message sendMessage(SendMessageCommand cmd) {
        var msg = new Message(cmd);
        this.messages.add(msg);
        this.lastMessageAt = cmd.sentAt();
        this.lastMessagePreview = cmd.content().substring(0, Math.min(30, cmd.content().length()));
        this.unreadCount += 1;
        //TODO: registerEvent(new MessageSent(this.id, msg.getId()));
        return msg;
    }

    /**
     * Marks a specific message as read and updates the unread counter.
     *
     * @param cmd command containing message ID to mark as read
     * */
    public void markMessageRead(MarkMessageReadCommand cmd) {
        this.messages.stream()
                .filter(m -> m.getId().value().equals(cmd.messageId()))
                .findFirst()
                .ifPresent(m -> {
                    m.markAsRead(cmd);
                    this.unreadCount = Math.max(0, this.unreadCount - 1);
                });
    }

    public void closeChat(CloseChatCommand cmd) {
        if (this.id.value().equals(cmd.chatId())) {
            this.status = ChatStatus.CLOSED;
            //TODO: registerEvent(new ChatClosed(this.id));
        }
    }
}
