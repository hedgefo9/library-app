package com.hedgefo9.libraryapp.subscriptionservice.service;

import com.hedgefo9.libraryapp.events.BookService.BookCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendBookCreatedNotification(BookCreatedEvent event, Long userId) {
        log.info("Отправка пользователю {} уведомления о создании книги: {}", userId, event.getBase().toString());
    }
}
