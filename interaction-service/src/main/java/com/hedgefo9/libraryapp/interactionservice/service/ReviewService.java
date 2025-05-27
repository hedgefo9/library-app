package com.hedgefo9.libraryapp.interactionservice.service;

import com.hedgefo9.libraryapp.interactionservice.dto.ReviewDto;
import com.hedgefo9.libraryapp.interactionservice.entity.Review;
import com.hedgefo9.libraryapp.interactionservice.mapper.ReviewMapper;
import com.hedgefo9.libraryapp.interactionservice.repository.ReviewRepository;
import com.hedgefo9.libraryapp.interactionservice.util.DataChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
	private final ReviewRepository reviewRepository;
	private final ReviewMapper reviewMapper;
	private final DataChecker dataChecker;

	@Transactional
	public ReviewDto addReview(ReviewDto reviewDto) {
		dataChecker.assertUserAndBookExistOrThrow(reviewDto.bookId(), reviewDto.userId());

		return reviewMapper.entityToDto(reviewRepository.save(reviewMapper.dtoToEntity(reviewDto)));
	}

	@Transactional(readOnly = true)
	public ReviewDto getReview(Long bookId, Long userId) {
		return reviewMapper.entityToDto(reviewRepository.findReviewByBookIdAndUserId(bookId, userId).orElseThrow(() -> new IllegalArgumentException("Review not found")));
	}

	@Transactional
	public ReviewDto deleteReview(Long bookId, Long userId) {
		return reviewMapper.entityToDto(reviewRepository.removeReviewByBookIdAndUserId(bookId, userId)
				.orElseThrow(() -> new IllegalArgumentException("Review not found")));
	}

	@Transactional(readOnly = true)
	public List<ReviewDto> getReviewsByBookId(Long id, Pageable pageable) {
		return reviewMapper.entityListToDtoList(reviewRepository.findReviewsByBookId(id, pageable));
	}

	@Transactional(readOnly = true)
	public List<ReviewDto> getReviewsByUserId(Long id, Pageable pageable) {
		return reviewMapper.entityListToDtoList(reviewRepository.findReviewsByUserId(id, pageable));
	}

	@Transactional
	public ReviewDto updateReview(Long bookId, Long userId, ReviewDto reviewDto) {
		Review updatedReview = reviewMapper.updateEntityFromDto(reviewDto,
				reviewRepository.findReviewByBookIdAndUserId(bookId, userId)
						.orElseThrow(() -> new IllegalArgumentException("Review not found")));

		return reviewMapper.entityToDto(updatedReview);
	}
}
