package com.example.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user = userRepository.findByName(name);
		System.out.println("userは、" + user);
		if(user == null) {
			System.err.println("aaa");
			throw new UsernameNotFoundException("user not found");
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		System.out.println("customUserDetailsは、" + customUserDetails);
		return customUserDetails;
		
	}

}
