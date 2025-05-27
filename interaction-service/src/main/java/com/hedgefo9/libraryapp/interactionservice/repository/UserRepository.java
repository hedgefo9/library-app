package com.hedgefo9.libraryapp.interactionservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {
	private static final String KEY = "valid:users";
	private final StringRedisTemplate redis;

	public void addUserId(Long userId) {
		redis.opsForSet().add(KEY, userId.toString());
	}

	public void removeUserId(Long userId) {
		redis.opsForSet().remove(KEY, userId.toString());
	}

	public boolean isUserIdInSet(Long userId) {
		Boolean result = redis.opsForSet().isMember(KEY, userId.toString());
		return result != null && result;
	}
}