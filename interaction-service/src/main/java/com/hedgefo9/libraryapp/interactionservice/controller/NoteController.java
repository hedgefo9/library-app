package com.hedgefo9.libraryapp.interactionservice.controller;

import com.hedgefo9.libraryapp.interactionservice.dto.NoteDto;
import com.hedgefo9.libraryapp.interactionservice.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hedgefo9.libraryapp.interactionservice.config.ApiPaths.BASE_API;

@RestController
@RequestMapping(BASE_API + "/notes")
@RequiredArgsConstructor
public class NoteController {
	private final NoteService noteService;

	@PostMapping
	public NoteDto createNote(@RequestBody NoteDto note) {
		return noteService.addNote(note);
	}

	@PatchMapping("/book/{bookId}/user/{userId}")
	public NoteDto updateNote(@PathVariable Long bookId, @PathVariable Long userId, @RequestBody NoteDto note) {
		return noteService.updateNote(note, bookId, userId);
	}

	@GetMapping("/book/{bookId}/user/{userId}")
	public NoteDto getNote(@PathVariable Long bookId, @PathVariable Long userId) {
		return noteService.getNote(bookId, userId);
	}

	@GetMapping("/user/{userId}")
	public List<NoteDto> getAllUserNotes(@PathVariable Long userId, Pageable pageable) {
		return noteService.getAllUserNotes(userId, pageable);
	}

	@GetMapping("/book/{bookId}")
	public List<NoteDto> getAllBookNotes(@PathVariable Long bookId, Pageable pageable) {
		return noteService.getAllBookNotes(bookId, pageable);
	}

	@DeleteMapping("/book/{bookId}/user/{userId}")
	public NoteDto deleteNote(@PathVariable Long bookId, @PathVariable Long userId) {
		return noteService.deleteNote(bookId, userId);
	}
}
