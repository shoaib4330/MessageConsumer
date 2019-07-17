package com.kashegypt.gateway.notification.common.jpa.repository;

import com.kashegypt.gateway.notification.common.jpa.model.CustomerDevice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDeviceRepository extends CrudRepository<CustomerDevice, Long> {

    Optional<CustomerDevice> findByCustomerAccountIdAndDeviceIMEI(long customerAccountId, String deviceIMEI);
}
