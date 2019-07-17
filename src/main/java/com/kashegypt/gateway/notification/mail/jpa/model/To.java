package com.kashegypt.gateway.notification.mail.jpa.model;

import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class To {
    private Long userId;
    private String name;
    private String email;
    private Map<String, String> additionalData;
}
