package com.hedgefo9.libraryapp.interactionservice.entity.id;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReviewId implements Serializable {
	private Long bookId;
	private Long userId;
}
