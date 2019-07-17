package com.kashegypt.gateway.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kashegypt.gateway.notification.rest.api.exception.ErrorResponse;
import com.kashegypt.gateway.notification.rest.api.exception.KashEgyptException;
import com.kashegypt.gateway.notification.rest.api.type.impl.GeneralErrorType;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationErrorHandler implements AuthenticationFailureHandler {

    private final ObjectWriter errorResponseWriter = new ObjectMapper().writerFor(ErrorResponse.class);

    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON.toString());
        httpServletResponse.setCharacterEncoding("UTF-8");
        ErrorResponse errorResponse = ErrorResponse.builder().errorCode(GeneralErrorType.KEYCLOAK_ERROR.getErrorCode())//
                .message(GeneralErrorType.KEYCLOAK_ERROR.getErrorMessage())//
                .httpStatus(GeneralErrorType.KEYCLOAK_ERROR.getHttpStatusCode()).build();
        errorResponseWriter.writeValue(httpServletResponse.getWriter(), errorResponse);

    }
}
