package com.kashegypt.gateway.notification.rest.api.exception.interceptor;

import com.kashegypt.gateway.notification.rest.api.exception.ErrorResponse;
import com.kashegypt.gateway.notification.rest.api.exception.KashEgyptException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(KashEgyptException.class)
    public ResponseEntity<Object> handleException(KashEgyptException exception){
        ErrorResponse errorResponse = ErrorResponse.builder().errorCode(exception.getAppCode())//
                .message(exception.getAppMessage())//
                .httpStatus(exception.getHttpStatus()).errorCode(exception.getAppCode()).build();
        return ResponseEntity.status(exception.getHttpStatus()).body(errorResponse);
    }
}
