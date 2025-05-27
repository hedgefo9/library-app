package com.hedgefo9.libraryapp.interactionservice.listener;

import com.hedgefo9.libraryapp.events.BookService.BookCreatedEvent;
import com.hedgefo9.libraryapp.events.BookService.BookDeletedEvent;
import com.hedgefo9.libraryapp.interactionservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookEventListener {
	private final BookService bookService;

	@KafkaListener(topics = "book.created", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "bookCreatedKafkaListenerContainerFactory")
	public void onBookCreated(BookCreatedEvent event) {
		bookService.addBook(event.getBase().getId());
	}

	@KafkaListener(topics = "book.deleted", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "bookDeletedKafkaListenerContainerFactory")
	public void onBookDeleted(BookDeletedEvent event) {
		bookService.removeBook(event.getBookId());
	}
}
