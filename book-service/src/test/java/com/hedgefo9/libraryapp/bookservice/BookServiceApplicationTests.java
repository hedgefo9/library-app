package com.hedgefo9.libraryapp.bookservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class BookServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
