package com.pranav.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService detailsService;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
	{
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		/*
		http.csrf().disable().authorizeHttpRequests((authorize) -> //authorize.anyRequest().authenticated()
					authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
					.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults());
				*/
		http.csrf().disable().authorizeHttpRequests((authorize) -> //authorize.anyRequest().authenticated()
		authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
				.requestMatchers("/api/auth/**").permitAll()
		.anyRequest().authenticated()
	);
		return http.build();

	}

	/*
	// In memory User
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails pranav = org.springframework.security.core.userdetails.User.builder().username("pranav")
				.password(passwordEncoder().encode("pranav@123"))
				.roles("ADMIN")
				.build();
		
		UserDetails rupal = org.springframework.security.core.userdetails.User.builder()
								.username("rupal")
								.password(passwordEncoder().encode("rupal"))
								.roles("USER")
								.build();

		return new InMemoryUserDetailsManager(pranav, rupal);
	}
	*/
	
	
}
