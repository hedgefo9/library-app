package com.hedgefo9.libraryapp.bookservice.controller;

import com.hedgefo9.libraryapp.bookservice.dto.AddAuthorRequest;
import com.hedgefo9.libraryapp.bookservice.dto.UpdateAuthorRequest;
import com.hedgefo9.libraryapp.bookservice.entity.Author;
import com.hedgefo9.libraryapp.bookservice.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping
    public Author addAuthor(@RequestBody AddAuthorRequest author) {
        return authorService.addAuthor(author);
    }

    @PatchMapping
    public Author updateAuthor(@RequestBody UpdateAuthorRequest author) {
        return authorService.updateAuthor(author);
    }

    @DeleteMapping("/{authorId}")
    public Author deleteAuthor(@PathVariable Long authorId) {
        return authorService.deleteAuthor(authorId);
    }
}
