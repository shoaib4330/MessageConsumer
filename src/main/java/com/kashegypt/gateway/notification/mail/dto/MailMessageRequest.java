package com.kashegypt.gateway.notification.mail.dto;

import com.kashegypt.gateway.notification.mail.jpa.model.To;
import lombok.*;

import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MailMessageRequest {
    private Long languageId;
    private String templateName;
    private String from;
    private List<To> to;
    private To cc;
    private To bcc;
}
