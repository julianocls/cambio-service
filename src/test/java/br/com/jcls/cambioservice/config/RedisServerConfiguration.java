package br.com.jcls.cambioservice.config;

import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@TestConfiguration
public class RedisServerConfiguration {

    private static final int PORT = 6379;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() {
        redisServer = RedisServer.builder().port(PORT).build();
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        redisServer.stop();
    }

}
