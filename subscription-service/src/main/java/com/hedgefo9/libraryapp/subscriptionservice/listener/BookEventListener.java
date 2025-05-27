package com.hedgefo9.libraryapp.subscriptionservice.listener;

import com.hedgefo9.libraryapp.events.BookService.BookCreatedEvent;
import com.hedgefo9.libraryapp.subscriptionservice.service.SubscriptionEventProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookEventListener {
    private final SubscriptionEventProcessor subscriptionEventProcessor;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = {"book.created"}, containerFactory = "bookCreatedKafkaListenerContainerFactory")
    public void listenCreated(BookCreatedEvent event) {
        log.info("Получено событие о создании книги: {}", event.getBase());
        subscriptionEventProcessor.notifyBookCreated(event);
    }
}

