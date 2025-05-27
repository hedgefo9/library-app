package com.hedgefo9.libraryapp.interactionservice.service;

import com.hedgefo9.libraryapp.interactionservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public void addUser(Long userId) {
		userRepository.addUserId(userId);
	}

	public void removeUser(Long userId) {
		userRepository.removeUserId(userId);
	}

	public boolean isUserKnown(Long userId) {
		return userRepository.isUserIdInSet(userId);
	}
}