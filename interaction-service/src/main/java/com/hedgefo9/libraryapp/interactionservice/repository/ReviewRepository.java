package com.hedgefo9.libraryapp.interactionservice.repository;

import com.hedgefo9.libraryapp.interactionservice.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	Optional<Review> removeReviewByBookIdAndUserId(Long bookId, Long userId);

	List<Review> findReviewsByBookId(Long id, Pageable pageable);

	List<Review> findReviewsByUserId(Long userId, Pageable pageable);

	Optional<Review> findReviewByBookIdAndUserId(Long bookId, Long userId);
}
