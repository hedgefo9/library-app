package com.hedgefo9.libraryapp.searchservice.config;

import com.google.protobuf.Message;
import com.hedgefo9.libraryapp.events.BookService;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

	private static final String BOOTSTRAP_SERVERS = "localhost:9092";
	private static final String SCHEMA_REGISTRY_URL = "http://localhost:6081";
	private static final String GROUP_ID = "search-service-group";

	private <T extends Message> ConsumerFactory<Long, T> protobufConsumerFactory(Class<T> clazz) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put("schema.registry.url", SCHEMA_REGISTRY_URL);
		props.put("specific.protobuf.value.type", clazz.getName());

		var deserializer = new KafkaProtobufDeserializer<T>();
		deserializer.configure(props, false);

		return new DefaultKafkaConsumerFactory<>(
				props,
				new LongDeserializer(),
				deserializer
		);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Long, BookService.BookCreatedEvent> bookCreatedKafkaListenerContainerFactory() {
		var factory = new ConcurrentKafkaListenerContainerFactory<Long, BookService.BookCreatedEvent>();
		factory.setConsumerFactory(protobufConsumerFactory(BookService.BookCreatedEvent.class));
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Long, BookService.BookUpdatedEvent> bookUpdatedKafkaListenerContainerFactory() {
		var factory = new ConcurrentKafkaListenerContainerFactory<Long, BookService.BookUpdatedEvent>();
		factory.setConsumerFactory(protobufConsumerFactory(BookService.BookUpdatedEvent.class));
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Long, BookService.BookDeletedEvent> bookDeletedKafkaListenerContainerFactory() {
		var factory = new ConcurrentKafkaListenerContainerFactory<Long, BookService.BookDeletedEvent>();
		factory.setConsumerFactory(protobufConsumerFactory(BookService.BookDeletedEvent.class));
		return factory;
	}
}

