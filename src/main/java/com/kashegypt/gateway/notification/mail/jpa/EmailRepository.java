package com.kashegypt.gateway.notification.mail.jpa;

import com.kashegypt.gateway.notification.mail.jpa.model.Email;
import com.kashegypt.gateway.notification.mail.jpa.model.MailTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends CrudRepository<Email, Long> {
}
