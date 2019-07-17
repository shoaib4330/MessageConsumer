package com.kashegypt.gateway.notification.sms.mapper;

import com.kashegypt.gateway.notification.sms.jpa.model.SMS;
import com.kashegypt.gateway.notification.sms.jpa.model.SMSTemplate;
import com.kashegypt.gateway.notification.sms.request.SMSMessageJmsRequest;
import com.kashegypt.gateway.notification.sms.request.SMSRequestMessage;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.stereotype.Component;

@Component
public class SMSMapper {

    public SMSRequestMessage map(SMSMessageJmsRequest jmsRequest, SMSTemplate smsTemplate, String senderName) {

        return SMSRequestMessage.builder().senderName(senderName)//
                .recipients(jmsRequest.getPhoneNumber())//
                .messageText(StringSubstitutor.replace(smsTemplate.getBody(), jmsRequest.getPlaceHolderDataForBody()))//
                .build();

    }

    public SMS map(SMSRequestMessage smsRequestMessage, Long templateId,Long userId) {
        return SMS.builder().body(smsRequestMessage.getMessageText())//
                .phoneNumber(smsRequestMessage.getRecipients())//
                .templateId(templateId)//
                .userId(userId)
                .build();
    }


}
