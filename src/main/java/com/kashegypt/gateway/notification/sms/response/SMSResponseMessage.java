package com.kashegypt.gateway.notification.sms.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SMSResponseMessage implements Serializable {
    private static final long serialVersionUID = -1L;

    private int replyCode;
    private String replyMessage;
    private String requestId;
    private int clientRequestId;
    private String requestTime;
    private SMSResponseData data;
    private SMSResponseError error;
}
