package com.kashegypt.gateway.notification.sms.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SMSRequestMessage implements Serializable {
    private static final long serialVersionUID = -1L;
    private String messageText;
    private String senderName;
    @Builder.Default
    private String messageType = "text";
    private String recipients;
    @Builder.Default
    private int acknowledgement = 1;
    @Builder.Default
    private int flashing = 0;
}
