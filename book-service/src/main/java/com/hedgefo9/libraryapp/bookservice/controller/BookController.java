package com.hedgefo9.libraryapp.bookservice.controller;

import com.hedgefo9.libraryapp.bookservice.dto.BookDto;
import com.hedgefo9.libraryapp.bookservice.entity.Book;
import com.hedgefo9.libraryapp.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody BookDto book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable("bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody BookDto book) {
        return bookService.updateBook(bookId, book);
    }

    @GetMapping("/author")
    public List<Book> getBooksByAuthor(@RequestParam("author") Long authorId) {
        return bookService.getBooksByAuthor(authorId);
    }

    @PostMapping("/{bookId}/authors")
    public Book addAuthorToBook(@PathVariable("bookId") Long bookId, @RequestBody  Long authorId) {
        return bookService.addAuthorToBook(bookId, authorId);
    }

    @DeleteMapping("/{bookId}/authors")
    public Book removeAuthorFromBook(@PathVariable("bookId") Long bookId, @RequestBody  Long authorId) {
        return bookService.removeAuthorFromBook(bookId, authorId);
    }
}
