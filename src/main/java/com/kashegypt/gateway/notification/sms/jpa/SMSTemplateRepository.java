package com.kashegypt.gateway.notification.sms.jpa;

import com.kashegypt.gateway.notification.sms.jpa.model.SMSTemplate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SMSTemplateRepository extends CrudRepository<SMSTemplate, Long> {
    @Cacheable(cacheNames = "sms-templates")
    Optional<SMSTemplate> findByNameAndLanguageId(String name, int languageId);
}
