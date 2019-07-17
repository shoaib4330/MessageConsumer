package com.kashegypt.gateway.notification.sms;

import com.kashegypt.gateway.notification.rest.api.exception.KashEgyptException;
import com.kashegypt.gateway.notification.rest.api.type.impl.ConsumerErrorType;
import com.kashegypt.gateway.notification.sms.jpa.SMSRepository;
import com.kashegypt.gateway.notification.sms.jpa.SMSTemplateRepository;
import com.kashegypt.gateway.notification.sms.jpa.model.SMSTemplate;
import com.kashegypt.gateway.notification.sms.mapper.SMSMapper;
import com.kashegypt.gateway.notification.sms.request.SMSMessageJmsRequest;
import com.kashegypt.gateway.notification.sms.request.SMSRequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class SMSQueueListener {

    private final Logger log = LoggerFactory.getLogger(SMSQueueListener.class);

    @Autowired
    private SMSTemplateRepository smsTemplateRepository;

    @Autowired
    private SMSRepository smsRepository;

    @Autowired
    private SMSMapper smsMapper;

    @Autowired
    private SMSService smsService;

    @Value("${cequens.sender.name}")
    private String senderName;


    @JmsListener(destination = "${jms.queue.destination.sms}", containerFactory = "smsListenerContainerFactory")
    public void onSendSMSRequestMessage(final @Valid SMSMessageJmsRequest message, @Header(name = JmsHeaders.REDELIVERED) Boolean redelivered) {

        log.info("SMS request read from queue : {}", message.toString());

        SMSTemplate smsTemplate = getSmsTemplate(message.getTemplateName(), message.getLanguageId());

        SMSRequestMessage smsRequestMessage = smsMapper.map(message, smsTemplate, senderName);

        if (!redelivered) {
            smsRepository.save(smsMapper.map(smsRequestMessage,smsTemplate.getId(),message.getUserId()));
        }

        smsService.sendSMS(smsRequestMessage);
    }


    private SMSTemplate getSmsTemplate(String templateName, int languageId) {
        return smsTemplateRepository.findByNameAndLanguageId(templateName, languageId).orElseThrow(() -> new KashEgyptException(ConsumerErrorType.NO_TEMPLATE_WITH_NAME));
    }
}
