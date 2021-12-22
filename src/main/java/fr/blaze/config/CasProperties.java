package fr.blaze.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("cas")
@Data 
public class CasProperties {
    private String baseUrl;
    private String callBackUrl;
    private String loginEndpoint;
}
