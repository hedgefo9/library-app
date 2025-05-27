package com.hedgefo9.libraryapp.interactionservice.controller;

import com.hedgefo9.libraryapp.interactionservice.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hedgefo9.libraryapp.interactionservice.config.ApiPaths.BASE_API;
import static com.hedgefo9.libraryapp.interactionservice.config.ApiPaths.ID_PATH;

@RestController
@RequestMapping(BASE_API + "/likes")
@RequiredArgsConstructor
public class LikeController {
	private final LikeService likeService;

	@PostMapping("/book/{bookId}/user/{userId}")
	public void addLike(@PathVariable Long bookId, @PathVariable Long userId) {
		likeService.addLike(bookId, userId);
	}

	@DeleteMapping("/book/{bookId}/user/{userId}")
	public void removeLike(@PathVariable Long bookId, @PathVariable Long userId) {
		likeService.removeLike(bookId, userId);
	}

	@GetMapping("/user" + ID_PATH)
	public List<Long> getUserLikes(@PathVariable Long id, Pageable pageable) {
		return likeService.getUserLikes(id, pageable);
	}

	@GetMapping("/book/{bookId}/count")
	public Long getBookLikeCount(@PathVariable Long bookId) {
		return likeService.getBookLikeCount(bookId);
	}

	@GetMapping("/book/{bookId}/user/{userId}")
	public boolean hasUserLikedBook(@PathVariable Long bookId, @PathVariable Long userId) {
		return likeService.hasUserLikedBook(bookId, userId);
	}
}
