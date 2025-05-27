package com.hedgefo9.libraryapp.interactionservice;

import org.springframework.boot.SpringApplication;

public class TestInteractionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(InteractionServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
