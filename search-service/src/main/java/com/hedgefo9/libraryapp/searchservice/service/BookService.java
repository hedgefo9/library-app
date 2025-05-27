package com.hedgefo9.libraryapp.searchservice.service;

import com.hedgefo9.libraryapp.searchservice.model.Book;
import com.hedgefo9.libraryapp.searchservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;

	public Page<Book> searchBooks(String query, Pageable pageable) {
		return bookRepository.searchByTitleDescriptionOrAuthorName(query, pageable);
	}
}