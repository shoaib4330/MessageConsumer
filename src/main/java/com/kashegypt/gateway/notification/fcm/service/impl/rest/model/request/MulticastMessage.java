package com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request;

import lombok.*;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class MulticastMessage extends Message implements Serializable {
    private static final long serialVersionUID = -1L;
    private String topic;

    @Builder
    public MulticastMessage(Notification notification, @Singular("data") Map<String, String> data, String topic) {
        super(notification, data);
        this.topic = topic;
    }
}
