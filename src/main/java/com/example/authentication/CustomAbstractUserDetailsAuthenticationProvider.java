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
{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

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
