package br.com.jcls.cambioservice.config;

import br.com.jcls.cambioservice.CambioServiceApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = CambioServiceApplication.class)
@ExtendWith(SpringExtension.class)
class RedisConfigTest {

    @InjectMocks
    private RedisConfig redisConfig;

    @Test
    public void cacheConfiguration() {
        var redisCheConfiguration = redisConfig.createCacheConfiguration(60L);
        assertNotNull(redisCheConfiguration);
    }
}