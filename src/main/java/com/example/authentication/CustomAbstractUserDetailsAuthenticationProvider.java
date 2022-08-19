package com.example.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.entity.User;
import com.example.repository.UserRepository;

public class CustomAbstractUserDetailsAuthenticationProvider 
extends AbstractUserDetailsAuthenticationProvider
//implements AuthenticationProvider
{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// implements AuthenticationProvider の方
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		String name = (String) authentication.getPrincipal();
//		String password = (String) authentication.getCredentials();
//		System.out.println("name:" + name);
//		System.out.println("password:" + password);
//		return null;
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	
	// extends AbstractUserDetailsAuthenticationProvider の方
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		String password = (String) authentication.getCredentials();
		User user1 = userRepository.findByName(username);
		if(bCryptPasswordEncoder.matches(password, user1.getPassword())) {
			return new CustomUserDetails(user1);
		}else {
			throw new UsernameNotFoundException("user not found");
		}
		
	}

}
