package com.hedgefo9.libraryapp.interactionservice.repository;

import com.hedgefo9.libraryapp.interactionservice.entity.Like;
import com.hedgefo9.libraryapp.interactionservice.entity.id.LikeId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {

	Long countLikesByBookId(Long bookId);

	List<Long> findBookIdsByUserId(Long userId, Pageable pageable);
}
