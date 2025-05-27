package com.hedgefo9.libraryapp.subscriptionservice;

import org.springframework.boot.SpringApplication;

public class TestSubscriptionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(SubscriptionServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
