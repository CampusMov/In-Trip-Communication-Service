package com.campusmov.platform.intripcommunicationservice.shared.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Base abstract class for all aggregate roots that require auditing metadata.
 * <p>
 * This class centralizes common audit fields such as {@code createdAt} and {@code updatedAt},
 * and integrates Spring Data JPA auditing through the {@link AuditingEntityListener}.
 * <p>
 * It also extends {@link AbstractAggregateRoot} to support domain events within
 * a Domain-Driven Design (DDD) architecture.
 *
 * @param <T> the concrete aggregate root type extending this class
 */
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AuditableAbstractAggregateRoot<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {


    /**
     * Unique identifier of the aggregate root.
     * <p>
     * Uses a UUID string as primary key to ensure global uniqueness across distributed systems.
     */
    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * Timestamp indicating when the entity was created.
     * <p>
     * Automatically populated by Spring Data JPA auditing
     * and immutable after creation ({@code updatable = false}).
     */

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    /**
     * Timestamp indicating the last time the entity was modified.
     * <p>
     * Automatically updated by Spring Data JPA auditing on each update.
     */
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;

}
