package com.hedgefo9.libraryapp.subscriptionservice.config;

import com.google.protobuf.Message;
import com.hedgefo9.libraryapp.events.BookEvents;
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
    private static final String GROUP_ID = "subscription-service-group";

    private <T extends Message> ConsumerFactory<Long, T> protobufConsumerFactory(Class<T> clazz) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put("schema.registry.url", SCHEMA_REGISTRY_URL);
        // обязательно для Protobuf десериализатора:
        props.put("specific.protobuf.value.type", clazz.getName());

        var deserializer = new KafkaProtobufDeserializer<T>();
        // чтобы слушатель использовал конкретный тип:
        deserializer.configure(props, false);

        return new DefaultKafkaConsumerFactory<>(
                props,
                new LongDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, BookEvents.BookCreatedEvent> bookCreatedKafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<Long, BookEvents.BookCreatedEvent>();
        factory.setConsumerFactory(protobufConsumerFactory(BookEvents.BookCreatedEvent.class));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, BookEvents.BookDeletedEvent> bookDeletedKafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<Long, BookEvents.BookDeletedEvent>();
        factory.setConsumerFactory(protobufConsumerFactory(BookEvents.BookDeletedEvent.class));
        return factory;
    }
}
