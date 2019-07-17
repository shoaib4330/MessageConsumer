package com.kashegypt.gateway.notification.mail.mapper;

import com.google.common.collect.ImmutableMap;
import com.kashegypt.gateway.notification.mail.jpa.model.Email;
import com.kashegypt.gateway.notification.mail.jpa.model.To;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MailMapper {

    public String mapDataToPlaceHolders(String placeHolderString, Map<String, String> data) {
        return StringSubstitutor.replace(placeHolderString, data);
    }

    public Email map(String templateName, String subject, String title, String body, String from, List<To> to, To cc, To bcc, Long languageId, Map<String, String> mailBodyData, Long userId){
        return Email.builder()
                .sender(from)
                .receiver(to)
                .templateName(templateName)
                .subject(subject)
                .title(title)
                .body(body)
                .cc(cc)
                .bcc(bcc)
                .languageId(languageId)
                .mailBodyData(mailBodyData)
                .userId(userId)
                .build();
    }
}
