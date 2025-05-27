package com.hedgefo9.libraryapp.subscriptionservice.service;

import com.hedgefo9.libraryapp.events.BookEvents;
import com.hedgefo9.libraryapp.events.BookEvents.BookCreatedEvent;
import com.hedgefo9.libraryapp.subscriptionservice.repository.SubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubscriptionEventProcessor {

    private final SubscriptionRepository repo;
    private final NotificationService notifier;
    @Qualifier("applicationTaskExecutor")
    private final Executor executor;

    public void notifyBookCreated(BookCreatedEvent evt) {
        // Формируем ключи для всех авторов + жанр
        List<String> keys = evt.getBase().getAuthorsList().stream().map(BookEvents.Author::getId)
                .map(id -> "subscribers:author:" + id)
                .collect(Collectors.toList());

        // Сканим и шлём асинхронно
            repo.scanSubscribersUnion(keys, batch -> {
            for (Long userId : batch) {
                executor.execute(() -> notifier.sendBookCreatedNotification(evt, userId));
            }
        });
    }
}
