package com.kashegypt.gateway.notification.fcm.service.impl.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.kashegypt.gateway.notification.fcm.service.impl.rest.model.response.error.FcmPushNotificationErrorResponseMessage;
import com.kashegypt.gateway.notification.rest.api.exception.KashEgyptException;
import com.kashegypt.gateway.notification.rest.api.type.impl.ConsumerErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class FcmPushNotificationResponseErrorHandler implements ResponseErrorHandler {

    private ObjectReader jsonReader = new ObjectMapper().readerFor(FcmPushNotificationErrorResponseMessage.class);

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public void handleError(ClientHttpResponse response) {
        FcmPushNotificationErrorResponseMessage message;
        try {
            message = jsonReader.readValue(response.getBody());
            log.info("response:"+message.toString());

        } catch (Exception ex) {
            log.error("Exception occured:",ex);
            throw new KashEgyptException(ConsumerErrorType.INTERNAL_SERVER_ERROR);
        }
        log.error("Error occured while sending push notification");
        throw new KashEgyptException(ConsumerErrorType.INTERNAL_SERVER_ERROR);

    }
}