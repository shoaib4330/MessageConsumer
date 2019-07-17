package com.kashegypt.gateway.notification.rest.api.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = -1L;

    private Long id;
    private Date createdTimestamp;
    private Boolean isActive;
    private Long userId;
    private int notificationType;
    private int communicationType;
    private String templateName;
    private String title;
    private String body;
    private Map<String, String> additionalData;
}
