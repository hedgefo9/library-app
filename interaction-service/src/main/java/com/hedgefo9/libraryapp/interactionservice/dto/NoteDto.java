	package com.hedgefo9.libraryapp.interactionservice.dto;

	public record NoteDto(
			Long bookId,
			Long userId,
			String content
	) {
	}
