package com.kashegypt.gateway.notification.jms.consumer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnicastPushNotificationRequestMessage extends PushNotificationRequestMessage implements Serializable {
    private static final long serialVersionUID = -1L;

    @NotNull(message = "userId must not be null")
    private Long userId;

    @NotBlank(message = "fcmRegistrationId must not be null or blank")
    private String fcmRegistrationId;


}
