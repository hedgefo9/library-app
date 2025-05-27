package com.hedgefo9.libraryapp.interactionservice.util;

import com.hedgefo9.libraryapp.interactionservice.service.BookService;
import com.hedgefo9.libraryapp.interactionservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataChecker {
	private final UserService userService;
	private final BookService bookService;

	public void assertUserAndBookExistOrThrow(Long userId, Long bookId) {
		if (bookService.isBookKnown(bookId)) {
			throw new IllegalArgumentException("Book does not exist");
		}
		if (userService.isUserKnown(userId)) {
			throw new IllegalArgumentException("User does not exist");
		}
	}
}
