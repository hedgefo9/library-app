package com.hedgefo9.libraryapp.interactionservice.service;

import com.hedgefo9.libraryapp.interactionservice.entity.Like;
import com.hedgefo9.libraryapp.interactionservice.entity.id.LikeId;
import com.hedgefo9.libraryapp.interactionservice.repository.LikeRepository;
import com.hedgefo9.libraryapp.interactionservice.util.DataChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
	private final LikeRepository likeRepository;
	private final DataChecker dataChecker;

	@Transactional
	public void addLike(Long bookId, Long userId) {
		dataChecker.assertUserAndBookExistOrThrow(bookId, userId);

		likeRepository.save(new Like(bookId, userId));
	}

	@Transactional
	public void removeLike(Long bookId, Long userId) {
		likeRepository.delete(new Like(bookId, userId));
	}

	@Transactional(readOnly = true)
	public List<Long> getUserLikes(Long id, Pageable pageable) {
		return likeRepository.findBookIdsByUserId(id, pageable);
	}

	@Transactional(readOnly = true)
	public Long getBookLikeCount(Long bookId) {
		return likeRepository.countLikesByBookId(bookId);
	}

	@Transactional(readOnly = true)
	public boolean hasUserLikedBook(Long bookId, Long userId) {
		return likeRepository.existsById(new LikeId(bookId, userId));
	}
}
