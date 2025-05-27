package com.hedgefo9.libraryapp.bookservice.service;

import com.hedgefo9.libraryapp.bookservice.entity.Book;
import com.hedgefo9.libraryapp.events.BookEvents;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BookEventProducer {
    private final KafkaTemplate<Long, BookEvents.BookCreatedEvent> bookCreatedEventKafkaTemplate;
    private final KafkaTemplate<Long, BookEvents.BookDeletedEvent> bookDeletedEventKafkaTemplate;

    public void sendBookCreatedEvent(Book addedBook) {
        BookEvents.BookCreatedEvent event = BookEvents.BookCreatedEvent.newBuilder().setBase(
                BookEvents.BookEvent.newBuilder()
                        .setBookId(addedBook.getId())
                        .setIsbn(addedBook.getIsbn())
                        .setPublicationYear(addedBook.getPublicationYear())
                        .setDescription(addedBook.getDescription())
                        .setTitle(addedBook.getTitle())
                        .setGenre(addedBook.getGenre())
                        .setEventTime(Timestamp.from(Instant.now()).getTime())
        ).build();

        bookCreatedEventKafkaTemplate.send("book.created", event.getBase().getBookId(), event);
    }

    public void sendBookDeletedEvent(Long bookId) {
        BookEvents.BookDeletedEvent event = BookEvents.BookDeletedEvent.newBuilder().setBookId(bookId).build();

        bookDeletedEventKafkaTemplate.send("book.deleted", event.getBookId(), event);
    }
}
