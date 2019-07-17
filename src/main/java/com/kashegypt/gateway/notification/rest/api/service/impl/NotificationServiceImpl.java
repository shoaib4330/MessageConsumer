package com.kashegypt.gateway.notification.rest.api.service.impl;

import com.kashegypt.gateway.notification.common.jpa.model.CustomerDevice;
import com.kashegypt.gateway.notification.common.jpa.repository.CustomerAccountRepository;
import com.kashegypt.gateway.notification.common.jpa.repository.CustomerDeviceRepository;
import com.kashegypt.gateway.notification.common.jpa.repository.NotificationRepository;
import com.kashegypt.gateway.notification.jms.consumer.model.type.CommunicationType;
import com.kashegypt.gateway.notification.rest.api.dto.BooleanResponse;
import com.kashegypt.gateway.notification.rest.api.dto.FcmDeviceRegistrationDetailsDTO;
import com.kashegypt.gateway.notification.rest.api.dto.NotificationDTO;
import com.kashegypt.gateway.notification.rest.api.exception.KashEgyptException;
import com.kashegypt.gateway.notification.rest.api.service.NotificationService;
import com.kashegypt.gateway.notification.rest.api.service.mapper.NotificationMapper;
import com.kashegypt.gateway.notification.rest.api.type.impl.ConsumerErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private CustomerDeviceRepository customerDeviceRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    @Transactional
    public BooleanResponse saveFcmDeviceRegistrationId(@Valid FcmDeviceRegistrationDetailsDTO fcmDeviceRegistrationDetailsDTO) {
        CustomerDevice customerDevice = customerDeviceRepository
                .findByCustomerAccountIdAndDeviceIMEI(fcmDeviceRegistrationDetailsDTO.getUserId(), fcmDeviceRegistrationDetailsDTO.getDeviceIMEI())
                .orElseThrow(() -> new KashEgyptException(ConsumerErrorType.USER_DEVICE_NOT_FOUND_FOR_ID_AND_IMEI));
        customerDevice.setFcmDeviceRegistrationId(fcmDeviceRegistrationDetailsDTO.getFcmDeviceRegistrationId());
        return BooleanResponse.success();
    }

    @Override
    public List<NotificationDTO> getCustomerNotifications(@NotNull(message = "customerId must not be null") Long customerId) {
        customerAccountRepository.findByIdAndIsActive(customerId,true).orElseThrow(()->new KashEgyptException(ConsumerErrorType.CUSTOMER_NOT_FOUND_WITH_ID));
        //Promotional messages should be visible to all customers so adding multicast notifications in list as well
        return notificationMapper.map(notificationRepository.findAllByUserIdOrCommunicationTypeOrderByCreatedTimestampDesc(customerId,CommunicationType.MULTICAST.getId()));
    }
}
