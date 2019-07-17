package com.kashegypt.gateway.notification.fcm.service.impl;

import com.kashegypt.gateway.notification.fcm.service.FcmPushNotificationService;
import com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request.FcmPushNotificationMessage;
import com.kashegypt.gateway.notification.fcm.service.impl.rest.model.response.success.FcmPushNotificationResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FcmPushNotificationServiceImpl implements FcmPushNotificationService {

    private final Logger log = LoggerFactory.getLogger(FcmPushNotificationServiceImpl.class);

    @Autowired
    private RestTemplate fcmRestTemplate;

    @Value("${google.fcm.url}")
    private String googleFcmUrl;


    @Override
    public boolean sendFcmPushNotification(FcmPushNotificationMessage fcmPushNotificationMessage) {
        FcmPushNotificationResponseMessage response = fcmRestTemplate.postForObject(googleFcmUrl, fcmPushNotificationMessage, FcmPushNotificationResponseMessage.class);
        log.info("Successfully send FCM push notification, response received : {}", response);
        return response != null && response.getName().length() > 0;
    }
}
