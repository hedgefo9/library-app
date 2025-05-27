package com.hedgefo9.libraryapp.bookservice.dto;

public record BookDto (
        String isbn,
        Integer publicationYear,
        String title,
        String genre,
        String description,
        Integer[] authorIds
) { }
