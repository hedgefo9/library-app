package com.hedgefo9.libraryapp.subscriptionservice.service;

import com.hedgefo9.libraryapp.events.BookService.BookCreatedEvent;

public interface NotificationService {
    void sendBookCreatedNotification(BookCreatedEvent event, Long userId);
}
