package com.kashegypt.gateway.notification.jms.consumer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MulticastPushNotificationRequestMessage extends PushNotificationRequestMessage implements Serializable {
    private static final long serialVersionUID = -1L;

    @NotBlank(message = "topicName must not be null or blank")
    private String topicName;

}
