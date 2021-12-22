package fr.blaze.config;

import lombok.Data;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwt")
@Data
public class JwtProperties {
    private Duration validity;

    private String secret;
}
