package com.kashegypt.gateway.notification.fcm.service.impl.rest.model.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Notification implements Serializable {
    private static final long serialVersionUID = -1L;
    private String title;
    private String body;
}
