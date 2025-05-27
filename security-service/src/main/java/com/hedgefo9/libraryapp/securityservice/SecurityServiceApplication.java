package com.hedgefo9.libraryapp.securityservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityServiceApplication {

	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(SecurityServiceApplication.class, args);
	}

}
