package com.kashegypt.gateway.notification.jms.consumer.mapper;

import com.kashegypt.gateway.notification.common.jpa.model.Notification;
import com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request.FcmPushNotificationMessage;
import com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request.MulticastMessage;
import com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request.UnicastMessage;
import com.kashegypt.gateway.notification.jms.consumer.model.MulticastPushNotificationRequestMessage;
import com.kashegypt.gateway.notification.jms.consumer.model.PushNotificationRequestMessage;
import com.kashegypt.gateway.notification.jms.consumer.model.UnicastPushNotificationRequestMessage;
import com.kashegypt.gateway.notification.jms.consumer.model.type.CommunicationType;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Component
public class PushNotificationMessageMapper {

    private final DateFormat createdTimestampFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

    public FcmPushNotificationMessage map(UnicastPushNotificationRequestMessage unicastPushNotificationRequestMessage, Integer notificationType, String bodyTemplate, String titleTemplate) {

        return FcmPushNotificationMessage
                .builder()
                .message(UnicastMessage
                        .builder()
                        .token(unicastPushNotificationRequestMessage.getFcmRegistrationId())
                        .notification(createNotification(unicastPushNotificationRequestMessage, titleTemplate, bodyTemplate))
                        .data(unicastPushNotificationRequestMessage.getAdditionalData()!=null ? unicastPushNotificationRequestMessage.getAdditionalData():Collections.emptyMap())
                        .data("template",unicastPushNotificationRequestMessage.getTemplateName())
                        .data("title", StringSubstitutor.replace(titleTemplate, unicastPushNotificationRequestMessage.getPlaceHolderDataForTitle()))
                        .data("body", StringSubstitutor.replace(bodyTemplate, unicastPushNotificationRequestMessage.getPlaceHolderDataForBody()))
                        .data("notificationType", String.valueOf(notificationType))
                        .data("createdTimestamp", createdTimestampFormat.format(unicastPushNotificationRequestMessage.getCreatedTimestamp()))
                        .data("userId", String.valueOf(unicastPushNotificationRequestMessage.getUserId()))
                        .build())
                .build();
    }


    public FcmPushNotificationMessage map(MulticastPushNotificationRequestMessage multicastPushNotificationRequestMessage, String bodyTemplate, String titleTemplate) {

        return FcmPushNotificationMessage
                .builder()
                .message(MulticastMessage
                        .builder()
                        .topic(multicastPushNotificationRequestMessage.getTopicName())
                        .notification(createNotification(multicastPushNotificationRequestMessage, titleTemplate, bodyTemplate))
                        .data(multicastPushNotificationRequestMessage.getAdditionalData()!=null ?multicastPushNotificationRequestMessage.getAdditionalData():Collections.emptyMap())
                        .data("template",multicastPushNotificationRequestMessage.getTemplateName())
                        .data("title", StringSubstitutor.replace(titleTemplate, multicastPushNotificationRequestMessage.getPlaceHolderDataForTitle()))
                        .data("body", StringSubstitutor.replace(bodyTemplate, multicastPushNotificationRequestMessage.getPlaceHolderDataForBody()))
                        .data("createdTimestamp", createdTimestampFormat.format(multicastPushNotificationRequestMessage.getCreatedTimestamp()))
                        .build())
                .build();
    }

    private com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request.Notification createNotification(PushNotificationRequestMessage pushNotificationRequestMessage, String titleTemplate, String bodyTemplate) {
        return com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request.Notification
                .builder()
                .title(StringSubstitutor.replace(titleTemplate, pushNotificationRequestMessage.getPlaceHolderDataForTitle()))
                .body(StringSubstitutor.replace(bodyTemplate, pushNotificationRequestMessage.getPlaceHolderDataForBody()))
                .build();
    }

    public Notification map(Long templateId, int notificationType, long userId, Map<String, String> additionalData, String body, String title, Date createdTimestamp) {
        return Notification
                .builder()
                .title(title)
                .templateId(templateId)
                .body(body)
                .additionalData(additionalData)
                .notificationType(notificationType)
                .createdTimestamp(createdTimestamp)
                .communicationType(CommunicationType.UNICAST.getId())
                .userId(userId)
                .build();

    }

    public Notification map(Long templateId,int notificationType, String topicName, Map<String, String> additionalData, String body, String title, Date createdTimestamp) {
        return Notification
                .builder()
                .title(title)
                .body(body)
                .templateId(templateId)
                .additionalData(additionalData)
                .notificationType(notificationType)
                .createdTimestamp(createdTimestamp)
                .communicationType(CommunicationType.MULTICAST.getId())
                .topicName(topicName)
                .build();

    }


}
