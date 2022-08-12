package br.com.jcls.cambioservice.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@TestConfiguration
@Configuration
public class TestRedisConfiguration {

    private static final int REDIS_PORT = 6379;
    private RedisServer redisServer;

    public TestRedisConfiguration() {
        this.redisServer = new RedisServer(REDIS_PORT);
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
