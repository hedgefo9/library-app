package com.hedgefo9.libraryapp.interactionservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookRepository {
	private static final String KEY = "valid:books";
	private final StringRedisTemplate redis;

	public void addBookId(Long bookId) {
		redis.opsForSet().add(KEY, bookId.toString());
	}

	public void removeBookId(Long bookId) {
		redis.opsForSet().remove(KEY, bookId.toString());
	}

	public boolean isBookIdInSet(Long bookId) {
		Boolean result = redis.opsForSet().isMember(KEY, bookId.toString());
		return result != null && result;
	}
}
