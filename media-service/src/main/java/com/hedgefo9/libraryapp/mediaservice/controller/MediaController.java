package com.hedgefo9.libraryapp.mediaservice.controller;

import com.hedgefo9.libraryapp.mediaservice.dto.MediaMetadataResponse;
import com.hedgefo9.libraryapp.mediaservice.dto.MediaUploadResponse;
import com.hedgefo9.libraryapp.mediaservice.dto.PresignResponse;
import com.hedgefo9.libraryapp.mediaservice.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {

	private final MediaService mediaService;

	@PostMapping
	public ResponseEntity<MediaUploadResponse> upload(
			@RequestParam("file") MultipartFile file,
			@RequestHeader("X-Uploaded-By") String uploadedBy
	) {
		MediaUploadResponse response = mediaService.upload(file, uploadedBy);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MediaMetadataResponse> getMetadata(@PathVariable UUID id) {
		MediaMetadataResponse response = mediaService.getMetadata(id);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}/download")
	public ResponseEntity<InputStreamResource> download(@PathVariable UUID id) {
		InputStreamResource resource = mediaService.download(id);
		MediaMetadataResponse meta = mediaService.getMetadata(id);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(meta.contentType()))
				.contentLength(meta.sizeBytes())
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + meta.objectKey().substring(meta.objectKey().lastIndexOf('/') + 1) + "\"")
				.body(resource);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		mediaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<Page<MediaMetadataResponse>> list(Pageable pageable) {
		Page<MediaMetadataResponse> page = mediaService.list(pageable);
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}/presign")
	public ResponseEntity<PresignResponse> presign(
			@PathVariable UUID id,
			@RequestParam(name = "expires", defaultValue = "3600") int expires
	) {
		PresignResponse response = mediaService.presign(id, expires);
		return ResponseEntity.ok(response);
	}
}

