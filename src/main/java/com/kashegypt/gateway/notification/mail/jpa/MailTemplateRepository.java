package com.kashegypt.gateway.notification.mail.jpa;

import com.kashegypt.gateway.notification.mail.jpa.model.MailTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailTemplateRepository extends CrudRepository<MailTemplate, Long> {
    Optional<MailTemplate> findByNameAndLanguageId(String name, Long languageId);
}
