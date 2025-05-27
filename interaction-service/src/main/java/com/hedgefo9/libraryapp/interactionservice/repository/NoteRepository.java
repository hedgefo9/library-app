package com.hedgefo9.libraryapp.interactionservice.repository;

import com.hedgefo9.libraryapp.interactionservice.entity.Note;
import com.hedgefo9.libraryapp.interactionservice.entity.id.NoteId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, NoteId> {
	List<Note> findAllByUserId(Long userId, Pageable pageable);

	List<Note> findAllByBookId(Long bookId, Pageable pageable);
}
