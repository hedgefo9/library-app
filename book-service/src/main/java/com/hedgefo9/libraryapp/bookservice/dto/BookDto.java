package com.hedgefo9.libraryapp.bookservice.dto;

public record BookDto (
        Long id,
        String isbn,
        Integer publicationYear,
        String genre,
        String title,
        String description,
        Double averageRating,
        Integer reviewCount,
        String thumbnailUrl,
        String contentFileS3Key,
        Long[] authorIds
) { }
