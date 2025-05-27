package com.hedgefo9.libraryapp.subscriptionservice.service;

import com.hedgefo9.libraryapp.events.BookService.Author;
import com.hedgefo9.libraryapp.events.BookService.BookCreatedEvent;
import com.hedgefo9.libraryapp.subscriptionservice.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionEventProcessor {

    private final SubscriptionRepository repo;
    private final NotificationService notifier;
    @Qualifier("applicationTaskExecutor")
    private final Executor executor;

    public void notifyBookCreated(BookCreatedEvent evt) {
        List<String> keys = evt.getBase().getAuthorsList().stream().map(Author::getId)
                .map(id -> "subscribers:author:" + id)
                .collect(Collectors.toList());

            repo.scanSubscribersUnion(keys, batch -> {
            for (Long userId : batch) {
                executor.execute(() -> notifier.sendBookCreatedNotification(evt, userId));
            }
        });
    }
}
