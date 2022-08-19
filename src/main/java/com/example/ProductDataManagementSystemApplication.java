package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProductDataManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDataManagementSystemApplication.class, args);
//		String pass = "TestPassword1";
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		System.out.println(bCryptPasswordEncoder.encode(pass));
//		
	}

}
