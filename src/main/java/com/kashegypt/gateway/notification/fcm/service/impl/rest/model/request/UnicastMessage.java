package com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UnicastMessage extends Message implements Serializable {
    private static final long serialVersionUID = -1L;
    private String token;

    @Builder
    public UnicastMessage(Notification notification, @Singular("data") Map<String, String> data, String token) {
        super(notification, data);
        this.token = token;
    }
}
