package com.kashegypt.gateway.notification.sms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class SMSRecipientId implements Serializable {
    private static final long serialVersionUID = -1L;

    @JsonProperty(value = "SMSID")
    private String smsId;
}
