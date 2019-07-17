package com.kashegypt.gateway.notification.common.jpa.repository;

import com.kashegypt.gateway.notification.common.jpa.model.CustomerAccount;
import com.kashegypt.gateway.notification.common.jpa.model.CustomerDevice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, Long> {

    Optional<CustomerAccount> findByIdAndIsActive(long customerAccountId, boolean isActive);
}
