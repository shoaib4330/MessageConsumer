package com.kashegypt.gateway.notification.fcm.service.impl.rest.model.response.success;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FcmPushNotificationResponseMessage implements Serializable {
    private static final long serialVersionUID = -1L;
    private String name;
}
