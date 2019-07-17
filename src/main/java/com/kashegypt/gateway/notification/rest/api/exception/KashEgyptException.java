package com.kashegypt.gateway.notification.rest.api.exception;

import com.kashegypt.gateway.notification.rest.api.type.ApiErrorType;
import org.springframework.http.HttpStatus;


public class KashEgyptException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    protected HttpStatus httpStatus;
    protected String appCode;
    protected String appMessage;

    public KashEgyptException(ApiErrorType apiErrorType, Exception e) {
        super(e);
        appMessage = apiErrorType.getErrorMessage();
        appCode = apiErrorType.getErrorCode();
        httpStatus = apiErrorType.getHttpStatusCode();
    }

    public KashEgyptException(ApiErrorType apiErrorType) {
        super(apiErrorType.getErrorMessage());
        appMessage = apiErrorType.getErrorMessage();
        appCode = apiErrorType.getErrorCode();
        httpStatus = apiErrorType.getHttpStatusCode();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getAppCode() {
        return this.appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppMessage() {
        return this.appMessage;
    }

    public void setAppMessage(String appMessage) {
        this.appMessage = appMessage;
    }
}
