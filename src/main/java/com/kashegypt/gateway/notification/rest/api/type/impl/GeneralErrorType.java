package com.kashegypt.gateway.notification.rest.api.type.impl;


import com.kashegypt.gateway.notification.rest.api.type.ApiErrorType;
import org.springframework.http.HttpStatus;


public enum GeneralErrorType implements ApiErrorType {
    INVALID_INPUT(3001, "Invalid input", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR(3002, "Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_NAME_GENERATOR_NOT_SET(3003, "File name generator not set", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_LOADING_CONFIGURATION_FROM_DB(3004, "Error loading configuration properties from database", HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_LOADING_CONFIGURATION_APPLICATION_OR_PROFILE_NOT_SET(3005, "Error loading configuration properties from database, [application] or [profile] property not set, Please set [application] and [profile] properties either as JVM args, Environment variables or defining them in a file by the name (microprofile-config.properties)", HttpStatus.INTERNAL_SERVER_ERROR),
    KEYCLOAK_ERROR(3006, "Error from keycloak", HttpStatus.UNAUTHORIZED),
    INVALID_ID(3007, "Invalid id provided", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_USER(3008, "User is not allowed to perform this action.", HttpStatus.FORBIDDEN);

    private static final String PREFIX = "KE-";
    private HttpStatus httpStatusCode;
    private int code;
    private String message;

    GeneralErrorType(int code, String message, HttpStatus httpStatusCode) {
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
