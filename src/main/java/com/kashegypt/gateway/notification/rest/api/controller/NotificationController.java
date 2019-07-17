package com.kashegypt.gateway.notification.rest.api.controller;

import com.kashegypt.gateway.notification.rest.api.dto.BooleanResponse;
import com.kashegypt.gateway.notification.rest.api.dto.FcmDeviceRegistrationDetailsDTO;
import com.kashegypt.gateway.notification.rest.api.dto.NotificationDTO;
import com.kashegypt.gateway.notification.rest.api.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification/api/v1")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

        @PostMapping("/device/fcm-registration-id/save")
    public BooleanResponse saveFcmDeviceRegistrationId(@RequestBody FcmDeviceRegistrationDetailsDTO fcmDeviceRegistrationDetailsDTO) {
        return notificationService.saveFcmDeviceRegistrationId(fcmDeviceRegistrationDetailsDTO);
    }

    @GetMapping("notifications/{customerId}")
    public List<NotificationDTO> getCustomerNotifications(@PathVariable("customerId") Long customerId) {
        return notificationService.getCustomerNotifications(customerId);
    }
}
