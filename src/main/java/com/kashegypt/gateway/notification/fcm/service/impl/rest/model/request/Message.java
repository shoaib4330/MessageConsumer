package com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private static final long serialVersionUID = -1L;
    private Notification notification;
    private Map<String, String> data;
}
