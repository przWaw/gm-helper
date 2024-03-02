package com.pWaw.gmhelper.DataManipulation.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {

    public Caffeine<Object, Object> caffeineConfig(){
        return Caffeine.newBuilder()
                .initialCapacity(15)
                .maximumSize(50)
                .expireAfterAccess(5, TimeUnit.MINUTES)
                .expireAfterWrite(3, TimeUnit.MINUTES);
    }

    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
