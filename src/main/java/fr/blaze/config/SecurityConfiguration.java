package fr.blaze.config;

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
import fr.blaze.service.impl.BlazeUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenProvider tokenProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();

		http.headers().frameOptions().disable();

		http.authorizeRequests()
				.antMatchers("/login/**").permitAll()
				.antMatchers("/api/version").permitAll()
				.anyRequest().authenticated();

		http.exceptionHandling()
				.authenticationEntryPoint(casAuthenticationEntryPoint());

		http.addFilterBefore(casAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterAfter(new TokenGeneratorFilter(tokenProvider), CasAuthenticationFilter.class);
	}

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(casAuthenticationProvider());
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
		CasAuthenticationProvider authenticationProvider = new CasAuthenticationProvider();
		authenticationProvider.setAuthenticationUserDetailsService(customUserDetailsService());
		authenticationProvider.setServiceProperties(serviceProperties());
		authenticationProvider.setTicketValidator(serviceTicketValidator());
		authenticationProvider.setKey("an_id_for_this_auth_provider_only");
		return authenticationProvider;
	}

	@Bean
	public AuthenticationUserDetailsService<CasAssertionAuthenticationToken> customUserDetailsService() {
		return new BlazeUserDetailsService();
	}

	@Bean
	public Cas30ServiceTicketValidator serviceTicketValidator() {
		return new Cas30ServiceTicketValidator("http://localhost:8080/cas/");
	}

	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter authenticationFilter = new CasAuthenticationFilter();
		authenticationFilter.setAuthenticationManager(authenticationManager());
		return authenticationFilter;
	}

	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint authenticationEntryPoint = new CasAuthenticationEntryPoint();
		authenticationEntryPoint.setLoginUrl("http://localhost:8080/cas/login");
		authenticationEntryPoint.setServiceProperties(serviceProperties());
		return authenticationEntryPoint;
	}
}