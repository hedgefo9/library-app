package com.hedgefo9.libraryapp.searchservice.listener;

import com.hedgefo9.libraryapp.events.BookService;
import com.hedgefo9.libraryapp.searchservice.mapper.BookMapper;
import com.hedgefo9.libraryapp.searchservice.model.Book;
import com.hedgefo9.libraryapp.searchservice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookEventListener {
	private final BookRepository bookRepository;
	private final BookMapper bookMapper;

	@KafkaListener(
			topics = "book.created",
			groupId = "${spring.kafka.consumer.group-id}",
			containerFactory = "bookCreatedKafkaListenerContainerFactory"
	)
	public void listenBookCreated(BookService.BookCreatedEvent event) {
		Book book = bookMapper.fromProto(event.getBase());
		bookRepository.save(book);
	}

	@KafkaListener(
			topics = "book.updated",
			groupId = "${spring.kafka.consumer.group-id}",
			containerFactory = "bookUpdatedKafkaListenerContainerFactory"
	)
	public void listenBookUpdated(BookService.BookUpdatedEvent event) {
		Book book = bookMapper.fromProto(event.getBase());
		bookRepository.save(book);
	}

	@KafkaListener(
			topics = "book.deleted",
			groupId = "${spring.kafka.consumer.group-id}",
			containerFactory = "bookDeletedKafkaListenerContainerFactory"
	)
	public void listenBookDeleted(BookService.BookDeletedEvent event) {
		bookRepository.deleteById(event.getBookId());
	}
}