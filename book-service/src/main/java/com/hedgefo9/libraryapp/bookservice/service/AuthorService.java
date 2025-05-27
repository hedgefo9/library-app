package com.hedgefo9.libraryapp.bookservice.service;

import com.hedgefo9.libraryapp.bookservice.dto.AuthorDto;
import com.hedgefo9.libraryapp.bookservice.dto.BookDto;
import com.hedgefo9.libraryapp.bookservice.entity.Author;
import com.hedgefo9.libraryapp.bookservice.mapper.AuthorMapper;
import com.hedgefo9.libraryapp.bookservice.mapper.BookMapper;
import com.hedgefo9.libraryapp.bookservice.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    @Transactional
    public AuthorDto addAuthor(AuthorDto authorDto) {
        return authorMapper.entityToDto(authorRepository.save(authorMapper.dtoToEntity(authorDto)));
    }

    @Transactional(readOnly = true)
    public AuthorDto getAuthor(Long id) {
        return authorMapper.entityToDto(authorRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Author not found")));
    }

    @Transactional(readOnly = true)
    public Author getAuthorEntity(Long id) {
        return authorRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Author not found"));
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> getAllAuthors() {
        return authorMapper.entityListToDtoList(authorRepository.findAll());
    }

    @Transactional
    public AuthorDto updateAuthor(Long authorId, AuthorDto authorDto) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException("Author not found"));

        return authorMapper.entityToDto(authorRepository.save(authorMapper.updateEntityFromDto(authorDto, author)));
    }

    @Transactional
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }

    @Transactional
    public AuthorDto deleteAuthor(Long authorId) {
        var author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalArgumentException("Author not found"));
        for (var book : author.getBooks()) {
            book.getAuthors().remove(author);
        }
        authorRepository.save(author);
        authorRepository.delete(author);
        return authorMapper.entityToDto(author);
    }

    @Transactional(readOnly = true)
    public List<BookDto> getBooksByAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));
        return bookMapper.toDtoList(author.getBooks());
    }
}
