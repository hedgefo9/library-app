package com.hedgefo9.libraryapp.interactionservice.service;

import com.hedgefo9.libraryapp.interactionservice.dto.NoteDto;
import com.hedgefo9.libraryapp.interactionservice.entity.Note;
import com.hedgefo9.libraryapp.interactionservice.entity.id.NoteId;
import com.hedgefo9.libraryapp.interactionservice.mapper.NoteMapper;
import com.hedgefo9.libraryapp.interactionservice.repository.NoteRepository;
import com.hedgefo9.libraryapp.interactionservice.util.DataChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
	private final NoteRepository noteRepository;
	private final NoteMapper noteMapper;
	private final DataChecker dataChecker;

	@Transactional
	public NoteDto addNote(NoteDto noteDto) {
		dataChecker.assertUserAndBookExistOrThrow(noteDto.bookId(), noteDto.userId());

		return noteMapper.entityToDto(noteRepository.save(noteMapper.dtoToEntity(noteDto)));
	}

	@Transactional
	public NoteDto updateNote(NoteDto noteDto, Long bookId, Long userId) {
		Note note = noteRepository.findById(new NoteId(bookId, userId)).orElseThrow(() -> new RuntimeException("Note not found"));
		Note updatedNote = noteMapper.updateEntityFromDto(noteDto, note);

		return noteMapper.entityToDto(noteRepository.save(updatedNote));
	}

	@Transactional(readOnly = true)
	public NoteDto getNote(Long bookId, Long userId) {
		Note note = noteRepository.findById(new NoteId(bookId, userId)).orElseThrow(() -> new RuntimeException("Note not found"));
		return noteMapper.entityToDto(note);
	}

	@Transactional
	public NoteDto deleteNote(Long bookId, Long userId) {
		Note note = noteRepository.findById(new NoteId(bookId, userId)).orElseThrow(() -> new RuntimeException("Note not found"));
		noteRepository.deleteById(new NoteId(bookId, userId));
		return noteMapper.entityToDto(note);
	}

	@Transactional(readOnly = true)
	public List<NoteDto> getAllUserNotes(Long userId, Pageable pageable) {
		return noteMapper.entityListToDtoList(noteRepository.findAllByUserId(userId, pageable));
	}

	@Transactional(readOnly = true)
	public List<NoteDto> getAllBookNotes(Long userId, Pageable pageable) {
		return noteMapper.entityListToDtoList(noteRepository.findAllByBookId(userId, pageable));
	}
}
