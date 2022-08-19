package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig 
// extends WebSecurityConfigurerAdapter
{
	
	
//	@Autowired
//	private CustomUserDetailsService customUserDetailsService;
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/login/**", "/user/**", "/rest/**", "/img/**", "/css/**", "/js/**").permitAll()
//		.anyRequest().authenticated()
//		
//		.and()
//		.formLogin()
//		.loginPage("/login").permitAll()
//		.usernameParameter("name")
//		.passwordParameter("password")
//		.failureUrl("/login?failed")
//		.defaultSuccessUrl("/item/list")
//		
//		.and()
//		.logout()
//		.logoutUrl("/logout").permitAll()
//		.logoutSuccessUrl("/login")
//		.invalidateHttpSession(true)
//		.deleteCookies("JSESSIONID")
//		;
//	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.formLogin(login -> login
				.loginProcessingUrl("/login").permitAll()
				.loginPage("/login").permitAll()
				.usernameParameter("name")
				.passwordParameter("password")
				.failureUrl("/login?failed")
				.defaultSuccessUrl("/item/list")
				
			).logout(logout -> logout
				.logoutUrl("/logout").permitAll()
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
			
			).authorizeHttpRequests(authz -> authz
	            .mvcMatchers("/login/**", "/user/**", "/rest/**", "/img/**", "/css/**", "/js/**").permitAll()
	            .anyRequest().authenticated()
	        );
			
		
		return http.build();
			
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customUserDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
