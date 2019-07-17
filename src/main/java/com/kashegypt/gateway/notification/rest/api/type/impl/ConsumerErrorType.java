package com.kashegypt.gateway.notification.rest.api.type.impl;

import com.kashegypt.gateway.notification.rest.api.type.ApiErrorType;
import org.springframework.http.HttpStatus;


public enum ConsumerErrorType implements ApiErrorType {
    USER_DEVICE_NOT_FOUND_FOR_ID_AND_IMEI(7001, "No customer device found for given userId and deviceIMEI", HttpStatus.NOT_FOUND),
    CUSTOMER_NOT_FOUND_WITH_ID(7002, "No customer account found for given userId", HttpStatus.NOT_FOUND),
    NO_TEMPLATE_WITH_NAME(7003, "No template exists with given name", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(7004, "Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    private static final String PREFIX = "KE-";
    private HttpStatus httpStatusCode;
    private int code;
    private String message;

    ConsumerErrorType(int code, String message, HttpStatus httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public String getErrorCode() {
        return PREFIX + this.code;
    }

    @Override
    public String getErrorMessage() {
        return this.message;
    }

    @Override
    public HttpStatus getHttpStatusCode() {
        return this.httpStatusCode;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
