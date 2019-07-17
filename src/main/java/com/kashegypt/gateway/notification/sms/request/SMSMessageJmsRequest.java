package com.kashegypt.gateway.notification.sms.request;

import lombok.*;

import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SMSMessageJmsRequest {
    private String templateName;
    private String phoneNumber;
    private Long userId;
    private int languageId;
    private Map<String, String> placeHolderDataForBody;
}
