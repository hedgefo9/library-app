package com.hedgefo9.libraryapp.bookservice.service;

import com.hedgefo9.libraryapp.bookservice.dto.BookDto;
import com.hedgefo9.libraryapp.bookservice.entity.Author;
import com.hedgefo9.libraryapp.bookservice.entity.Book;
import com.hedgefo9.libraryapp.bookservice.mapper.BookMapper;
import com.hedgefo9.libraryapp.bookservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookEventProducer bookEventProducer;

    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks(Pageable pageable) {
        return bookMapper.toDtoList(bookRepository.findAll(pageable).getContent());
    }

    @Transactional(readOnly = true)
    public BookDto getBook(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::entityToDto)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    @Transactional
    public BookDto saveBook(BookDto book) {
        var addedBook = bookRepository.save(bookMapper.dtoToEntity(book));
        if (addedBook.getId() == null) {
            throw new IllegalArgumentException("Book not saved");
        }

        bookEventProducer.sendBookCreatedEvent(addedBook);
        return bookMapper.entityToDto(addedBook);
    }

    @Transactional
    public BookDto updateBook(Long bookId, BookDto book) {
        Book bookToUpdate = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Book updatedBook = bookRepository.save(bookMapper.updateFromDto(book, bookToUpdate));

        return bookMapper.entityToDto(updatedBook);
    }

    @Transactional
    public void deleteBook(Long id) {
        Book bookToDelete = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        for (Author author : bookToDelete.getAuthors()) {
            author.getBooks().remove(bookToDelete);
        }

        bookRepository.delete(bookToDelete);

        bookEventProducer.sendBookDeletedEvent(id);
    }

    @Transactional
    public BookDto addAuthorToBook(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Author author = authorService.getAuthorEntity(authorId);

        book.getAuthors().add(author);
        return bookMapper.entityToDto(bookRepository.save(book));
    }

    @Transactional
    public BookDto removeAuthorFromBook(Long bookId, Long authorId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Author author = authorService.getAuthorEntity(authorId);

        book.getAuthors().remove(author);
        return bookMapper.entityToDto(bookRepository.save(book));
    }
}
