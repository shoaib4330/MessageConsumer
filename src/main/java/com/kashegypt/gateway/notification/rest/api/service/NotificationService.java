package com.kashegypt.gateway.notification.rest.api.service;


import com.kashegypt.gateway.notification.rest.api.dto.BooleanResponse;
import com.kashegypt.gateway.notification.rest.api.dto.NotificationDTO;
import com.kashegypt.gateway.notification.rest.api.dto.FcmDeviceRegistrationDetailsDTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface NotificationService {

    BooleanResponse saveFcmDeviceRegistrationId(@Valid FcmDeviceRegistrationDetailsDTO fcmDeviceRegistrationDetailsDTO);

    List<NotificationDTO> getCustomerNotifications(@NotNull(message = "customerId must not be null") Long customerId);
}
