package com.kashegypt.gateway.notification.fcm.service.impl.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessTokenHeaderRequestInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    private FcmAccessTokenProvider fcmAccessTokenProvider;

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        HttpHeaders headers = request.getHeaders();
        headers.add("Authorization", "Bearer " + fcmAccessTokenProvider.getAccessToken());
        return execution.execute(request, body);
    }
}