package com.hedgefo9.libraryapp.bookservice.service;

import com.hedgefo9.libraryapp.bookservice.dto.AddAuthorRequest;
import com.hedgefo9.libraryapp.bookservice.dto.UpdateAuthorRequest;
import com.hedgefo9.libraryapp.bookservice.entity.Author;
import com.hedgefo9.libraryapp.bookservice.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author addAuthor(AddAuthorRequest author) {
        return authorRepository.save(new Author(
                author.name(),
                author.bio(),
                Timestamp.from(Instant.now())
        ));
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Author not found"));
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author updateAuthor(UpdateAuthorRequest author) {
        if (!authorRepository.existsById(author.getId())) {
            throw new IllegalArgumentException("Author not found");
        }
        return authorRepository.save(new Author(
                author.getId(),
                author.getName(),
                author.getBio()
        ));
    }

    public Boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }

    public Author deleteAuthor(Long authorId) {
        var author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException("Author not found"));
        for (var book : author.getBooks()) {
            book.getAuthors().remove(author);
        }
        authorRepository.save(author);
        authorRepository.delete(author);
        return author;
    }
}
