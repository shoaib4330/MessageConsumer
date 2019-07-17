package com.kashegypt.gateway.notification.sms.jpa;

import com.kashegypt.gateway.notification.sms.jpa.model.SMS;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SMSRepository extends CrudRepository<SMS, Long> {
}
