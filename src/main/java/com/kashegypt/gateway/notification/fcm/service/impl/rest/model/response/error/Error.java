package com.kashegypt.gateway.notification.fcm.service.impl.rest.model.response.error;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error implements Serializable {
    private static final long serialVersionUID = -1L;
    private int code;
    private String message;
    private String status;
}
