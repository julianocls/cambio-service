package br.com.jcls.cambioservice.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
//@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    public RedisCacheConfiguration createCacheConfiguration(long timeoutInMinutes) {
        return org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(timeoutInMinutes));
    }

    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory redisConnectionFactory, CacheTimeout cacheTimeout) {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();
        if (Objects.nonNull(cacheTimeout.getCachesTTL())) {
            for (Map.Entry<String, String> cacheNameAndTimeout : cacheTimeout.getCachesTTL().entrySet()) {
                cacheConfigurations.put(cacheNameAndTimeout.getKey(), createCacheConfiguration(Long.parseLong(cacheNameAndTimeout.getValue())));
            }
        }
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(createCacheConfiguration(Long.parseLong(cacheTimeout.getDefaultTTL())))
                .withInitialCacheConfigurations(cacheConfigurations).build();
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }
}
