package com.hedgefo9.libraryapp.bookservice.service;

import com.hedgefo9.libraryapp.bookservice.dto.BookDto;
import com.hedgefo9.libraryapp.bookservice.entity.Author;
import com.hedgefo9.libraryapp.bookservice.entity.Book;
import com.hedgefo9.libraryapp.bookservice.mapper.BookMapper;
import com.hedgefo9.libraryapp.bookservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookEventProducer bookEventProducer;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    public Book saveBook(BookDto book) {
        var addedBook = bookRepository.save(bookMapper.toBook(book));
        if (addedBook.getId() == null) {
            throw new IllegalArgumentException("Book not saved");
        }

        bookEventProducer.sendBookCreatedEvent(addedBook);
        return addedBook;
    }

    public Book updateBook(Long bookId, BookDto book) {
        Book bookToUpdate = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));

        return bookRepository.save(bookMapper.updateFromDto(book, bookToUpdate));
    }

    public void deleteBook(Long id) {
        Book bookToDelete = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        for (Author author : bookToDelete.getAuthors()) {
            author.getBooks().remove(bookToDelete);
        }

        bookRepository.delete(bookToDelete);

        bookEventProducer.sendBookDeletedEvent(id);
    }

    public List<Book> getBooksByAuthor(Long authorId) {
        Author author = authorService.getAuthorById(authorId);
        return bookRepository.findByAuthorsContains(author);
    }

    public Book addAuthorToBook(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Author author = authorService.getAuthorById(authorId);

        book.getAuthors().add(author);
        return bookRepository.save(book);
    }

    public Book removeAuthorFromBook(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Author author = authorService.getAuthorById(authorId);

        book.getAuthors().remove(author);
        return bookRepository.save(book);
    }
}
