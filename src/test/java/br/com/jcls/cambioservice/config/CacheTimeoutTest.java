package br.com.jcls.cambioservice.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import({RedisConfig.class, CacheTimeout.class})
@ExtendWith(MockitoExtension.class)
public class CacheTimeoutTest {

    @Autowired
    private CacheTimeout timeout;

    @BeforeAll
    public static void beforeClass() throws Exception {
        System.out.println("JUnit4To5.beforeClass");
    }
    @BeforeEach
    public void before() throws Exception {
        System.out.println("JUnit4To5.before");
    }
    @Test
    public void shouldMigrateASimpleTest() {

        var time = timeout.getCachesTTL();

        Assertions.assertEquals("expected", "expected");
    }

}