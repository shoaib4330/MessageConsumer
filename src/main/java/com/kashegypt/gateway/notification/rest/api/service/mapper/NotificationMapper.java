package com.kashegypt.gateway.notification.rest.api.service.mapper;

import com.kashegypt.gateway.notification.common.jpa.model.Notification;
import com.kashegypt.gateway.notification.rest.api.dto.NotificationDTO;
import com.kashegypt.gateway.notification.util.JsonUtil;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationMapper {

    public List<NotificationDTO> map(List<Notification> notifications) {
        if (notifications == null || notifications.isEmpty()) {
            return Collections.emptyList();
        }
        return notifications
                .stream()
                .map(notification -> map(notification))
                .collect(Collectors.toList());
    }

    public NotificationDTO map(Notification notification) {
        return NotificationDTO
                .builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .title(notification.getTitle())
                .templateName((notification.getNotificationTemplate()==null) ? "" : notification.getNotificationTemplate().getName())
                .body(notification.getBody())
                .additionalData(notification.getAdditionalData())
                .createdTimestamp(notification.getCreatedTimestamp())
                .notificationType(notification.getNotificationType())
                .communicationType(notification.getCommunicationType())
                .isActive(notification.getIsActive())
                .build();
    }
}
