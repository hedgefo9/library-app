package com.hedgefo9.libraryapp.searchservice.controller;

import com.hedgefo9.libraryapp.searchservice.model.Book;
import com.hedgefo9.libraryapp.searchservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.hedgefo9.libraryapp.searchservice.config.ApiPaths.BASE_API;

@RestController
public class BookController {

	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping(BASE_API + "/books/search")
	public Page<Book> searchBooks(
			@RequestParam("q") String query,
			Pageable pageable
	) {
		return bookService.searchBooks(query, pageable);
	}
}