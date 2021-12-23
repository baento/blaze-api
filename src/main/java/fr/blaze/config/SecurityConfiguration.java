package fr.blaze.config;

import java.util.HashSet;
import java.util.Set;

import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.blaze.security.TokenGeneratorFilter;
import fr.blaze.security.TokenProvider;
import fr.blaze.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(casAuthenticationEntryPoint()).and()
                .addFilterBefore(new TokenGeneratorFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilter(casAuthenticationFilter());

        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/api/version").permitAll()
                .anyRequest().authenticated();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(casAuthenticationProvider());
	}

    @Bean
	public Set<String> adminList() {
		Set<String> admins = new HashSet<String>();
		admins.add("admin");
		return admins;
	}

	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService("http://localhost:8081/login/cas");
		serviceProperties.setSendRenew(false);
		return serviceProperties;
	}

	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider.setTicketValidator(serviceTicketValidator());
		casAuthenticationProvider.setKey("an_id_for_this_auth_provider_only");
		return casAuthenticationProvider;
	}

	@Bean
	public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
		return new CustomUserDetailsService(adminList());
	}

	@Bean
	public Cas30ServiceTicketValidator serviceTicketValidator() {
		return new Cas30ServiceTicketValidator("http://localhost:8080/cas/");
	}

	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
		return casAuthenticationFilter;
	}

	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl("http://localhost:8080/cas/login");
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}
}