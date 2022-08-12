package br.com.jcls.cambioservice.service;

import br.com.jcls.cambioservice.config.CacheTimeout;
import br.com.jcls.cambioservice.config.RedisConfig;
import br.com.jcls.cambioservice.model.Cambio;
import br.com.jcls.cambioservice.repository.CambioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.Map;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import({RedisConfig.class, CambioService.class, CacheTimeout.class})
@EnableCaching
@ImportAutoConfiguration(classes = {CacheAutoConfiguration.class, RedisAutoConfiguration.class})
@ExtendWith(MockitoExtension.class)
public class CambioServiceTest {

    private static final String FROM = "USD";
    private static final String TO = "BRL";

    @Mock
    private CambioRepository repository;

    @Test
    public void shouldSaveUser_toRedis() {

        Mockito.when(repository.findByFromAndTo(FROM, TO)).thenReturn(createCambio("EUR"));

        var actual = repository.findByFromAndTo(FROM, TO);

        assertNotNull(actual);
    }

    private Cambio createCambio(String to) {
        return Cambio.builder()
                .id(1L)
                .from(FROM)
                .to(to)
                .environment("8000")
                .build();
    }
}