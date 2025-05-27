package com.hedgefo9.libraryapp.interactionservice.controller;

import com.hedgefo9.libraryapp.interactionservice.dto.ReviewDto;
import com.hedgefo9.libraryapp.interactionservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hedgefo9.libraryapp.interactionservice.config.ApiPaths.BASE_API;
import static com.hedgefo9.libraryapp.interactionservice.config.ApiPaths.ID_PATH;

@RestController
@RequestMapping(BASE_API + "/reviews")
@RequiredArgsConstructor
public class ReviewController {
	private final ReviewService reviewService;

	@PostMapping
	public ReviewDto addReview(@RequestBody ReviewDto review) {
		return reviewService.addReview(review);
	}

	@GetMapping("/book/{bookId}/user/{userId}")
	public ReviewDto getReview(@PathVariable Long bookId, @PathVariable Long userId) {
		return reviewService.getReview(bookId, userId);
	}

	@PatchMapping("/book/{bookId}/user/{userId}")
	public ReviewDto updateReview(@PathVariable Long bookId, @PathVariable Long userId, @RequestBody ReviewDto review) {
		return reviewService.updateReview(bookId, userId, review);
	}

    @DeleteMapping("/book/{bookId}/user/{userId}")
	public ReviewDto deleteReview(@PathVariable Long bookId, @PathVariable Long userId) {
		return reviewService.deleteReview(bookId, userId);
	}

	@GetMapping("/book" + ID_PATH)
	public List<ReviewDto> getReviewsByBookId(@PathVariable Long id, Pageable pageable) {
		return reviewService.getReviewsByBookId(id, pageable);
	}

	@GetMapping("/user" + ID_PATH)
	public List<ReviewDto> getReviewsByUserId(@PathVariable Long id, Pageable pageable) {
		return reviewService.getReviewsByUserId(id, pageable);
	}
}
