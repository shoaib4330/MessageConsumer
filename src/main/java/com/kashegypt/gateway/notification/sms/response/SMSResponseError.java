package com.kashegypt.gateway.notification.sms.response;

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
public class SMSResponseError implements Serializable {
    private static final long serialVersionUID = -1L;
     private int status;
     private String description;
    private  SMSResponseErrorDescription[] internalErrors;

}
