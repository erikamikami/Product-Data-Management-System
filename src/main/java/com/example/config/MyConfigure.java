package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

import com.example.authentication.CustomAbstractUserDetailsAuthenticationProvider;

@Configuration
public class MyConfigure {
	
	@Bean
	public AuthenticationProvider getAuthenticationProvider() {
		return new CustomAbstractUserDetailsAuthenticationProvider();
	}
	
}
