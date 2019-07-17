package com.kashegypt.gateway.notification.rest.api.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FcmDeviceRegistrationDetailsDTO implements Serializable {
    private static final long serialVersionUID = -1L;
    @NotNull(message = "userId must not be null")
    private Integer userId;
    @NotBlank(message = "deviceIMEI must not be null or empty")
    private String deviceIMEI;
    @NotNull(message = "fcmDeviceRegistrationId must not be null or empty")
    private String fcmDeviceRegistrationId;
}
