package com.exercise.projectschool.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
                new ConcurrentMapCache("countryClient"),
                new ConcurrentMapCache("findAllStudent"),
                new ConcurrentMapCache("findAllClassRoom"),
                new ConcurrentMapCache("findAllClassRoomPopolate")
                //Aggiungi altre cache se necessario
        ));
        return cacheManager;
    }

    /**Pulisce entrambe le cache ogni 24 ore*/
    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000) // 24 ore in millisecondi
    @CacheEvict(cacheNames = {"countryClient", "findAllStudent", "findAllClassRoom", "findAllClassRoomPopolate"}, allEntries = true)
    public void evictCache() {
    }
}
