package com.hedgefo9.libraryapp.interactionservice.entity;

import com.hedgefo9.libraryapp.interactionservice.entity.id.ReviewId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "reviews")
@Entity
@IdClass(ReviewId.class)
@Data
public class Review {
	@Id
	private Long bookId;
	@Id
	private Long userId;

	private String title;
	private String content;
	private Short rating;
}
