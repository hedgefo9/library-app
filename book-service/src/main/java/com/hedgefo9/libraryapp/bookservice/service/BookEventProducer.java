package com.hedgefo9.libraryapp.bookservice.service;

import com.hedgefo9.libraryapp.bookservice.entity.Book;
import com.hedgefo9.libraryapp.bookservice.mapper.BookMapper;
import com.hedgefo9.libraryapp.events.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookEventProducer {
    private final KafkaTemplate<Long, BookService.BookCreatedEvent> bookCreatedEventKafkaTemplate;
    private final KafkaTemplate<Long, BookService.BookDeletedEvent> bookDeletedEventKafkaTemplate;
    private final BookMapper bookMapper;

    public void sendBookCreatedEvent(Book addedBook) {
        BookService.BookCreatedEvent event = BookService.BookCreatedEvent.newBuilder().setBase(
                bookMapper.entityToProtoMessage(addedBook)
        ).build();

        bookCreatedEventKafkaTemplate.send("book.created", event.getBase().getId(), event);
    }

    public void sendBookDeletedEvent(Long bookId) {
        BookService.BookDeletedEvent event = BookService.BookDeletedEvent.newBuilder().setBookId(bookId).build();

        bookDeletedEventKafkaTemplate.send("book.deleted", event.getBookId(), event);
    }
}
