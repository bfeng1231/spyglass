package com.spyglass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true) // This enables authorization on methods
public class SpyglassApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpyglassApplication.class, args);
	}

}