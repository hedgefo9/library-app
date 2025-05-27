package com.hedgefo9.libraryapp.bookservice.dto;

public record AddBookRequest(
        String isbn,
        String title,
        String genre,
        String description,
        Integer[] authorIds
) { }
