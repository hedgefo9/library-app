package com.hedgefo9.libraryapp.bookservice.dto;

public record UpdateBookRequest(
        Long id,
        String isbn,
        String title,
        String genre
) {
}
