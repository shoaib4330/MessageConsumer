package com.kashegypt.gateway.notification;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheBeanConfiguration {

    @Value("${cache.fcm-access-token.ttl.minutes}")
    private int fcmAccessTokenCacheTtlInMinutes;

    @Value("${cache.notification-template.ttl.minutes}")
    private int templatesCacheTtlInMinutes;

    @Bean
    public CacheManager cacheManager(Ticker ticker) {
        CaffeineCache messageCache = buildCache("fcm-access-token", ticker, fcmAccessTokenCacheTtlInMinutes);
        CaffeineCache notificationCache = buildCache("notification-templates", ticker, templatesCacheTtlInMinutes);
        CaffeineCache smsCache = buildCache("sms-templates", ticker, templatesCacheTtlInMinutes);
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(messageCache, notificationCache, smsCache));
        return manager;
    }

    private CaffeineCache buildCache(String name, Ticker ticker, int minutesToExpire) {
        return new CaffeineCache(name, Caffeine.newBuilder().expireAfterWrite(minutesToExpire, TimeUnit.MINUTES).maximumSize(100).ticker(ticker).build());
    }

    @Bean
    public Ticker ticker() {
        return Ticker.systemTicker();
    }
}
