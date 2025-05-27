package com.hedgefo9.libraryapp.interactionservice.listener;

import com.hedgefo9.libraryapp.interactionservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventListener {
	private final UserService userService;

	@KafkaListener(topics = "user.created", groupId = "interaction-service-group")
	public void onUserCreated(Long userId) {
		userService.addUser(userId);
	}

	@KafkaListener(topics = "user.deleted", groupId = "interaction-service-group")
	public void onUserDeleted(Long userId) {
		userService.removeUser(userId);
	}
}