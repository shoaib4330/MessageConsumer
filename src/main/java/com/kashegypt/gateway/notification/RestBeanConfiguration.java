package com.kashegypt.gateway.notification;

import com.kashegypt.gateway.notification.fcm.service.impl.rest.AccessTokenHeaderRequestInterceptor;
import com.kashegypt.gateway.notification.fcm.service.impl.rest.FcmPushNotificationResponseErrorHandler;
import com.kashegypt.gateway.notification.sms.AccessTokenHeaderSmsRequestInterceptor;
import com.kashegypt.gateway.notification.sms.SMSResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestBeanConfiguration {

    @Autowired
    private AccessTokenHeaderRequestInterceptor accessTokenHeaderRequestInterceptor;

    @Autowired
    private AccessTokenHeaderSmsRequestInterceptor accessTokenHeaderSMSRequestInterceptor;

    @Bean
    @Scope("prototype")
    public RestTemplate fcmRestTemplate() {
        return new RestTemplateBuilder()
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new OkHttp3ClientHttpRequestFactory()))
                .additionalInterceptors(accessTokenHeaderRequestInterceptor)
                .errorHandler(new FcmPushNotificationResponseErrorHandler())
                .build();
    }

    @Bean
    @Scope("prototype")
    public RestTemplate smsRestTemplate() {
        return new RestTemplateBuilder()
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new OkHttp3ClientHttpRequestFactory()))
                .additionalInterceptors(accessTokenHeaderSMSRequestInterceptor)
                .errorHandler(new SMSResponseErrorHandler())
                .build();
    }
}
