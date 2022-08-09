package br.com.jcls.cambioservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "cache")
@Data
public class CacheConfigProperties {
    private String port;
    private String host;
    private String defaultTTL;
    private Map<String, String> cachesTTL;
}
