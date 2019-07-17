package com.kashegypt.gateway.notification.common.jpa.repository;

import com.kashegypt.gateway.notification.common.jpa.model.NotificationTemplate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationTemplateRepository extends CrudRepository<NotificationTemplate, Long> {
    @Cacheable(cacheNames = "notification-templates")
    Optional<NotificationTemplate> findByNameAndLanguageId(String name, int languageId);
}
