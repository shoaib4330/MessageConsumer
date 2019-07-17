package com.kashegypt.gateway.notification.fcm.service.impl.rest;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.kashegypt.gateway.notification.fcm.service.impl.FcmPushNotificationServiceImpl;
import com.kashegypt.gateway.notification.rest.api.exception.KashEgyptException;
import com.kashegypt.gateway.notification.rest.api.type.impl.ConsumerErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

@Component
public class FcmAccessTokenProvider {

    private final Logger log = LoggerFactory.getLogger(FcmPushNotificationServiceImpl.class);

    @Value("${fcm.access.token.request.scope}")
    private String fcmAccessTokenRequestScope;

    @Cacheable(cacheNames = "fcm-access-token")
    public String getAccessToken() {
        log.info("FCM access token has expired in cache so requesting new access token from Google");


        GoogleCredential googleCredential;

        try {
            googleCredential = GoogleCredential
                    .fromStream(new FileInputStream(ResourceUtils.getFile("classpath:fcm-private-key.json")))
                    .createScoped(Arrays.asList(fcmAccessTokenRequestScope));
            googleCredential.refreshToken();
            return googleCredential.getAccessToken();
        } catch (IOException ex) {
            throw new KashEgyptException(ConsumerErrorType.INTERNAL_SERVER_ERROR);
        }

    }
}
