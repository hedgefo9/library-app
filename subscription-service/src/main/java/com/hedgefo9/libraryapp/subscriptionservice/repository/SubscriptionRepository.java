package com.hedgefo9.libraryapp.subscriptionservice.repository;

import java.util.List;
import java.util.function.Consumer;

public interface SubscriptionRepository {

    void scanSubscribersUnion(List<String> resourceKeys, Consumer<List<Long>> consumer);

    void addSubscription(Long userId, Long authorId);

    void removeSubscription(Long userId, Long authorId);

    List<Long> findUserById(Long userId);

    void deleteByUserId(Long userId, Long authorId);
}

