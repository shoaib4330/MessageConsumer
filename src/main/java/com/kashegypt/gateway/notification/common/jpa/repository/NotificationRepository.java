package com.kashegypt.gateway.notification.common.jpa.repository;

import com.kashegypt.gateway.notification.common.jpa.model.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    List<Notification> findAllByUserIdOrCommunicationTypeOrderByCreatedTimestampDesc(Long customerId,int communicationType);
}
