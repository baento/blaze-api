package fr.blaze.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@Component
@ConfigurationProperties("cas")
public class CasProperties {
    private String baseUrl;
    private String callBackUrl;
    private String loginEndpoint;
}
