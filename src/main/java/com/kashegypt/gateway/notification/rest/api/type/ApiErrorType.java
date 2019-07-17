package com.kashegypt.gateway.notification.rest.api.type;

import org.springframework.http.HttpStatus;


public interface ApiErrorType {
    HttpStatus getHttpStatusCode();
    int getCode();
    String getErrorCode();
    String getErrorMessage();
}
