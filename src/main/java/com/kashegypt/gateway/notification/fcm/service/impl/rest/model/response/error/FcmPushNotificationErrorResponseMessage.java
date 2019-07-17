package com.kashegypt.gateway.notification.fcm.service.impl.rest.model.response.error;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FcmPushNotificationErrorResponseMessage implements Serializable {
    private static final long serialVersionUID = -1L;
    private Error error;
}
