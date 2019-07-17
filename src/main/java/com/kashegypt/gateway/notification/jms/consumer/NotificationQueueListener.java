package com.kashegypt.gateway.notification.jms.consumer;

import com.kashegypt.gateway.notification.common.jpa.model.NotificationTemplate;
import com.kashegypt.gateway.notification.common.jpa.repository.NotificationRepository;
import com.kashegypt.gateway.notification.common.jpa.repository.NotificationTemplateRepository;
import com.kashegypt.gateway.notification.fcm.service.FcmPushNotificationService;
import com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request.FcmPushNotificationMessage;
import com.kashegypt.gateway.notification.jms.consumer.mapper.PushNotificationMessageMapper;
import com.kashegypt.gateway.notification.jms.consumer.model.MulticastPushNotificationRequestMessage;
import com.kashegypt.gateway.notification.jms.consumer.model.UnicastPushNotificationRequestMessage;
import com.kashegypt.gateway.notification.rest.api.exception.KashEgyptException;
import com.kashegypt.gateway.notification.rest.api.type.impl.ConsumerErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class NotificationQueueListener {

    private final Logger log = LoggerFactory.getLogger(NotificationQueueListener.class);

    @Autowired
    private NotificationTemplateRepository notificationTemplateRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private PushNotificationMessageMapper pushNotificationMessageMapper;

    @Autowired
    private FcmPushNotificationService fcmPushNotificationService;


    @JmsListener(destination = "${jms.queue.destination}", selector = "JMSType = '#{T(com.kashegypt.gateway.notification.jms.consumer.model.type.CommunicationType).UNICAST.id}'")
    public void onUnicastPushNotificationRequestMessage(final @Valid UnicastPushNotificationRequestMessage message, @Header(name = JmsHeaders.REDELIVERED) Boolean redelivered) {

        log.info("Unicast push notification request read from queue : {}", message.toString());

        NotificationTemplate notificationTemplate = getPushNotificationTemplate(message.getTemplateName(), message.getLanguageId());

        FcmPushNotificationMessage fcmPushNotificationMessage = pushNotificationMessageMapper.map(message, notificationTemplate.getNotificationType(),notificationTemplate.getBody(), notificationTemplate.getTitle());

        if (!redelivered) {
            notificationRepository.save(pushNotificationMessageMapper.map(notificationTemplate.getId(), notificationTemplate.getNotificationType(), message.getUserId(), message.getAdditionalData(), fcmPushNotificationMessage.getMessage().getNotification().getBody(), fcmPushNotificationMessage.getMessage().getNotification().getTitle(), message.getCreatedTimestamp()));
        }

        fcmPushNotificationService.sendFcmPushNotification(fcmPushNotificationMessage);
    }

    @JmsListener(destination = "${jms.queue.destination}", selector = "JMSType = '#{T(com.kashegypt.gateway.notification.jms.consumer.model.type.CommunicationType).MULTICAST.id}'")
    public void onMulticastPushNotificationRequestMessage(final @Valid MulticastPushNotificationRequestMessage message, @Header(name = JmsHeaders.REDELIVERED) Boolean redelivered) {

        log.info("Multicast push notification request read from queue : {}", message.toString());

        NotificationTemplate notificationTemplate = getPushNotificationTemplate(message.getTemplateName(), message.getLanguageId());

        FcmPushNotificationMessage fcmPushNotificationMessage = pushNotificationMessageMapper.map(message, notificationTemplate.getBody(), notificationTemplate.getTitle());

        if (!redelivered) {
            notificationRepository.save(pushNotificationMessageMapper.map(notificationTemplate.getId(),notificationTemplate.getNotificationType(), message.getTopicName(), message.getAdditionalData(), fcmPushNotificationMessage.getMessage().getNotification().getBody(), fcmPushNotificationMessage.getMessage().getNotification().getTitle(), message.getCreatedTimestamp()));
        }

        fcmPushNotificationService.sendFcmPushNotification(fcmPushNotificationMessage);
    }

    private NotificationTemplate getPushNotificationTemplate(String templateName, int languageId) {
        return notificationTemplateRepository.findByNameAndLanguageId(templateName, languageId).orElseThrow(() -> new KashEgyptException(ConsumerErrorType.NO_TEMPLATE_WITH_NAME));
    }
}
