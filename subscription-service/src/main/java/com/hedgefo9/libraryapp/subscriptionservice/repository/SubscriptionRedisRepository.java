package com.hedgefo9.libraryapp.subscriptionservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

@Repository
@RequiredArgsConstructor
public class SubscriptionRedisRepository implements SubscriptionRepository {
    private final static int BATCH_SIZE = 1000;
    private final StringRedisTemplate redis;

    public String keyForAuthor(Long authorId) {
        return "subscribers:author:" + authorId;
    }

    public String keyForUser(Long userId) {
        return "subscriptions:user:" + userId + ":authors";
    }

    @Override
    public void scanSubscribersUnion(List<String> resourceKeys, Consumer<List<Long>> consumer) {
        String tempUnionKey = "temp:union:" + UUID.randomUUID();
        redis.opsForSet().unionAndStore(resourceKeys, tempUnionKey);

        ScanOptions opts = ScanOptions.scanOptions().count(BATCH_SIZE).build();
        try (Cursor<String> cursor = redis.opsForSet().scan(tempUnionKey, opts)) {
            List<Long> batch = new ArrayList<>(BATCH_SIZE);
            while (cursor.hasNext()) {
                batch.add(Long.valueOf(cursor.next()));
                if (batch.size() >= BATCH_SIZE) {
                    consumer.accept(batch);
                    batch.clear();
                }
            }
            if (!batch.isEmpty()) {
                consumer.accept(batch);
            }
        } finally {
            redis.delete(tempUnionKey);
        }
    }

    @Override
    public void addSubscription(Long userId, Long authorId) {
        redis.opsForSet().add(keyForAuthor(authorId), userId.toString());
        redis.opsForSet().add(keyForUser(userId), authorId.toString());
    }

    @Override
    public void removeSubscription(Long userId, Long authorId) {
        redis.opsForSet().remove(keyForAuthor(authorId), userId.toString());
        redis.opsForSet().remove(keyForUser(userId), authorId.toString());
    }

    @Override
    public List<Long> findUserById(Long userId) {
        Set<String> authorIds = redis.opsForSet().members(keyForUser(userId));
        if (authorIds == null) {
            return List.of();
        }
        return authorIds.stream().map(Long::valueOf).toList();
    }

    @Override
    public void deleteByUserId(Long userId, Long authorId) {
        redis.opsForSet().remove(keyForUser(userId), authorId.toString());
        redis.opsForSet().remove(keyForAuthor(authorId), userId.toString());
    }

}
