package com.kashegypt.gateway.notification.jms.consumer.model.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.kashegypt.gateway.notification.jms.consumer.NotificationQueueListener;
import com.kashegypt.gateway.notification.jms.consumer.model.MulticastPushNotificationRequestMessage;
import com.kashegypt.gateway.notification.jms.consumer.model.UnicastPushNotificationRequestMessage;
import com.kashegypt.gateway.notification.jms.consumer.model.type.CommunicationType;
import com.kashegypt.gateway.notification.rest.api.exception.KashEgyptException;
import com.kashegypt.gateway.notification.rest.api.type.impl.ConsumerErrorType;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class PushNotificationRequestMessageConverter implements MessageConverter {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectReader UnicastPushNotificationRequestMessageJsonReader = objectMapper.readerFor(UnicastPushNotificationRequestMessage.class);
    private final ObjectReader MulticastPushNotificationRequestMessageJsonReader = objectMapper.readerFor(MulticastPushNotificationRequestMessage.class);

    private final Logger log = LoggerFactory.getLogger(PushNotificationRequestMessageConverter.class);

    @Override
    public Message toMessage(Object o, Session session) throws MessageConversionException {
        //Not required right now
        return null;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {

        String payload = ((TextMessage) message).getText();
        try {
            switch (CommunicationType.getValuesMap().get(message.getIntProperty("JMSType"))) {
                case UNICAST: {
                    return UnicastPushNotificationRequestMessageJsonReader.readValue(payload);
                }
                case MULTICAST: {
                    return MulticastPushNotificationRequestMessageJsonReader.readValue(payload);
                }
            }
            return null;
        } catch (Exception ex) {
            log.error("Error occurred while reading payload",ex);
            throw new KashEgyptException(ConsumerErrorType.INTERNAL_SERVER_ERROR);
        }

    }
}
