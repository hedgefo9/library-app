package com.hedgefo9.libraryapp.interactionservice.service;

import com.hedgefo9.libraryapp.interactionservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;

	public void addBook(Long bookId) {
		bookRepository.addBookId(bookId);
	}

	public void removeBook(Long bookId) {
		bookRepository.removeBookId(bookId);
	}

	public boolean isBookKnown(Long bookId) {
		return bookRepository.isBookIdInSet(bookId);
	}
}