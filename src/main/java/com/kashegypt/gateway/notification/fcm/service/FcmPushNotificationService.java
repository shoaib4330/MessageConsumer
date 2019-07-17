package com.kashegypt.gateway.notification.fcm.service;

import com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request.FcmPushNotificationMessage;

public interface FcmPushNotificationService {

    boolean sendFcmPushNotification(FcmPushNotificationMessage fcmPushNotificationMessage);
}
