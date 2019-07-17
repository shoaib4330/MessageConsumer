package com.kashegypt.gateway.notification.sms.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.kashegypt.gateway.notification.rest.api.exception.KashEgyptException;
import com.kashegypt.gateway.notification.rest.api.type.impl.ConsumerErrorType;
import com.kashegypt.gateway.notification.sms.request.SMSMessageJmsRequest;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class SMSMessageConverter implements MessageConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectReader smsRequestJsonReader = objectMapper.readerFor(SMSMessageJmsRequest.class);

    @Override
    public Message toMessage(Object o, Session session) throws  MessageConversionException {
        // Not needed at the moment
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        String payload = ((TextMessage) message).getText();
        try {
            return smsRequestJsonReader.readValue(payload);
        } catch (Exception ex) {
            throw new KashEgyptException(ConsumerErrorType.INTERNAL_SERVER_ERROR);
        }
    }
}
