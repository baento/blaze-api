package fr.blaze.config;

import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import fr.blaze.security.TokenFilterConfigurer;
import fr.blaze.security.TokenProvider;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CasProperties casProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable();

        http.cors();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.apply(new TokenFilterConfigurer(tokenProvider));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .anyRequest();
                //.antMatchers("/login", "/login/validate")
                //.antMatchers("/version");
    }

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();

        serviceProperties.setService(casProperties.getCallBackUrl() + "/login/validate");
        serviceProperties.setSendRenew(false);

        return serviceProperties;
    }

    @Bean
    public TicketValidator ticketValidator() {
        return new Cas30ServiceTicketValidator(casProperties.getBaseUrl());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }
}