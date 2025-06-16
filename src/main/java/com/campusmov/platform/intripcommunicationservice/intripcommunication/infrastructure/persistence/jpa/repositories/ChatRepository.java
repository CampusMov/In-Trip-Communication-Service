package com.campusmov.platform.intripcommunicationservice.intripcommunication.infrastructure.persistence.jpa.repositories;

import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.aggregates.Chat;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.CarpoolId;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.ChatId;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.ChatStatus;
import com.campusmov.platform.intripcommunicationservice.intripcommunication.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, ChatId> {
    Optional<Chat> findByCarpoolIdAndPassengerId(CarpoolId carpoolId, UserId passengerId);
    boolean existsByCarpoolIdAndPassengerId(CarpoolId carpoolId, UserId passengerId);
    List<Chat> findAllByDriverId(UserId driverId);
}

