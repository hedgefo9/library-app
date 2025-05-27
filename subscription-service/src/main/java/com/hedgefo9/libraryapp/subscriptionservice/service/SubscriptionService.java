package com.hedgefo9.libraryapp.subscriptionservice.service;

import com.hedgefo9.libraryapp.subscriptionservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public void subscribeToAuthor(Long userId, Long authorId) {
        subscriptionRepository.addSubscription(userId, authorId);
    }

    public void unsubscribeFromAuthor(Long userId, Long authorId) {
        subscriptionRepository.removeSubscription(userId, authorId);
    }

    public List<Long> findAllSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findUserById(userId);
    }
}
