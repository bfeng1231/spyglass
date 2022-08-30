package com.spyglass.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncryptionConfiguration {
	
	@Bean
	public PasswordEncoder encoder() {
		// Salt, number of rounds
		return new BCryptPasswordEncoder(10);
	}
}
