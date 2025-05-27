package com.hedgefo9.libraryapp.bookservice.controller;

import com.hedgefo9.libraryapp.bookservice.dto.AuthorDto;
import com.hedgefo9.libraryapp.bookservice.dto.BookDto;
import com.hedgefo9.libraryapp.bookservice.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hedgefo9.libraryapp.bookservice.config.ApiPaths.BASE_API;
import static com.hedgefo9.libraryapp.bookservice.config.ApiPaths.ID_PATH;

@RestController
@RequestMapping(BASE_API + "/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping(ID_PATH)
    public AuthorDto getAuthor(@PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @PostMapping
    public AuthorDto addAuthor(@RequestBody AuthorDto author) {
        return authorService.addAuthor(author);
    }

    @PatchMapping(ID_PATH)
    public AuthorDto updateAuthor(@PathVariable Long id, @RequestBody AuthorDto author) {
        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping(ID_PATH)
    public AuthorDto deleteAuthor(@PathVariable Long id) {
        return authorService.deleteAuthor(id);
    }

    @GetMapping(ID_PATH + "/books")
    public List<BookDto> getBooksByAuthor(@PathVariable Long id) {
        return authorService.getBooksByAuthor(id);
    }
}
