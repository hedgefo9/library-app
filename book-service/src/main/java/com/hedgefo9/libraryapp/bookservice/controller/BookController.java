package com.hedgefo9.libraryapp.bookservice.controller;

import com.hedgefo9.libraryapp.bookservice.dto.BookDto;
import com.hedgefo9.libraryapp.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hedgefo9.libraryapp.bookservice.config.ApiPaths.BASE_API;

@RestController
@RequestMapping(BASE_API + "/books")
@RequiredArgsConstructor
public class BookController {
    public static final String BOOK_ID = "/{bookId}";
    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable);
    }

    @PostMapping
    public BookDto addBook(@RequestBody BookDto book) {
        return bookService.saveBook(book);
    }

    @GetMapping(BOOK_ID)
    public BookDto getBookById(@PathVariable("bookId") Long bookId) {
        return bookService.getBook(bookId);
    }

    @DeleteMapping(BOOK_ID)
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
    }

    @PatchMapping(BOOK_ID)
    public BookDto updateBook(@PathVariable Long bookId, @RequestBody BookDto book) {
        return bookService.updateBook(bookId, book);
    }

    @PostMapping(BOOK_ID + "/authors")
    public BookDto addAuthorToBook(@PathVariable Long bookId, @RequestBody Long authorId) {
        return bookService.addAuthorToBook(bookId, authorId);
    }

    @DeleteMapping("/{bookId}/authors")
    public BookDto removeAuthorFromBook(@PathVariable("bookId") Long bookId, @RequestBody Long authorId) {
        return bookService.removeAuthorFromBook(bookId, authorId);
    }
}
