package com.hedgefo9.libraryapp.subscriptionservice.controller;

import com.hedgefo9.libraryapp.subscriptionservice.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
@AllArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/users/{userId}/subscriptions/authors/{authorId}")
    public void subscribeToAuthor(@PathVariable Long userId, @PathVariable Long authorId) {
        subscriptionService.subscribeToAuthor(userId, authorId);
    }

    @DeleteMapping("/users/{userId}/subscriptions/authors/{authorId}")
    public void unsubscribeFromAuthor(@PathVariable Long userId, @PathVariable Long authorId) {
        subscriptionService.unsubscribeFromAuthor(userId, authorId);
    }

    @GetMapping("/users/{userId}/subscriptions")
    public List<Long> findAllSubscriptionsByUserId(@PathVariable Long userId) {
        return subscriptionService.findAllSubscriptionsByUserId(userId);
    }
}
