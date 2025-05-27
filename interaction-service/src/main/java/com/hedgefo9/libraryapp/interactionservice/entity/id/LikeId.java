package com.hedgefo9.libraryapp.interactionservice.entity.id;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LikeId implements Serializable {
	private Long bookId;
	private Long userId;
}
