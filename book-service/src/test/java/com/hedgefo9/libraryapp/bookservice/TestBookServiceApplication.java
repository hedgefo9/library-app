package com.hedgefo9.libraryapp.bookservice;

import org.springframework.boot.SpringApplication;

public class TestBookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(BookServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
