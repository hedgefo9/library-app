package com.hedgefo9.libraryapp.subscriptionservice.controller;

import com.hedgefo9.libraryapp.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/user/{userId}/author/{authorId}")
    public void subscribeToAuthor(@PathVariable Long userId, @PathVariable Long authorId) {
        subscriptionService.subscribeToAuthor(userId, authorId);
    }

    @DeleteMapping("/user/{userId}/author/{authorId}")
    public void unsubscribeFromAuthor(@PathVariable Long userId, @PathVariable Long authorId) {
        subscriptionService.unsubscribeFromAuthor(userId, authorId);
    }

    @GetMapping("/user/{userId}")
    public List<Long> findAllSubscriptionsByUserId(@PathVariable Long userId) {
        return subscriptionService.findAllSubscriptionsByUserId(userId);
    }
}
