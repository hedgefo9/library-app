package com.hedgefo9.libraryapp.interactionservice.dto;

public record ReviewDto(
		Long bookId,
		Long userId,
		String title,
		String content,
		Short rating
) {
}
