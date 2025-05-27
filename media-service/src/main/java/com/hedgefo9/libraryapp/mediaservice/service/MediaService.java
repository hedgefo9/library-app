package com.hedgefo9.libraryapp.mediaservice.service;

import com.hedgefo9.libraryapp.mediaservice.dto.MediaMetadataResponse;
import com.hedgefo9.libraryapp.mediaservice.dto.MediaUploadResponse;
import com.hedgefo9.libraryapp.mediaservice.dto.PresignResponse;
import com.hedgefo9.libraryapp.mediaservice.entity.Media;
import com.hedgefo9.libraryapp.mediaservice.repository.MediaRepository;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {
	private final MediaRepository mediaRepository;
	private final MinioClient minioClient;

	private final static String BUCKET = "media";

	@Transactional
	public MediaUploadResponse upload(MultipartFile file, String uploadedBy) {
		try {
			UUID id = UUID.randomUUID();
			String objectKey = id + "/" + file.getOriginalFilename();
			String contentType = file.getContentType() != null
					? file.getContentType()
					: "application/octet-stream";


			minioClient.putObject(
					PutObjectArgs.builder()
							.bucket(BUCKET)
							.object(objectKey)
							.stream(file.getInputStream(), file.getSize(), -1)
							.contentType(contentType)
							.build()
			);


			Media media = Media.builder()
					.id(id)
					.bucket(BUCKET)
					.objectKey(objectKey)
					.contentType(contentType)
					.sizeBytes(file.getSize())
					.uploadedBy(uploadedBy)
					.createdAt(Instant.now())
					.build();
			mediaRepository.save(media);

			String downloadUrl = "/media/" + id + "/download";
			return new MediaUploadResponse(id, downloadUrl, BUCKET);

		} catch (MinioException | java.io.IOException e) {
			throw new RuntimeException("Failed to upload media", e);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional(readOnly = true)
	public MediaMetadataResponse getMetadata(UUID id) {
		Media media = mediaRepository.findById(id)
				.orElseThrow(() -> new MediaNotFoundException(id));

		String downloadUrl = "/media/" + id + "/download";
		return new MediaMetadataResponse(
				media.getId(),
				media.getBucket(),
				media.getObjectKey(),
				media.getContentType(),
				media.getSizeBytes(),
				media.getCreatedAt(),
				media.getUploadedBy(),
				downloadUrl
		);
	}

	@Transactional(readOnly = true)
	public InputStreamResource download(UUID id) {
		Media media = mediaRepository.findById(id)
				.orElseThrow(() -> new MediaNotFoundException(id));
		try {
			InputStream is = minioClient.getObject(
					io.minio.GetObjectArgs.builder()
							.bucket(media.getBucket())
							.object(media.getObjectKey())
							.build()
			);
			return new InputStreamResource(is);
		} catch (Exception e) {
			throw new RuntimeException("Failed to download media", e);
		}
	}

	@Transactional
	public void delete(UUID id) {
		Media media = mediaRepository.findById(id)
				.orElseThrow(() -> new MediaNotFoundException(id));
		try {
			minioClient.removeObject(
					RemoveObjectArgs.builder()
							.bucket(media.getBucket())
							.object(media.getObjectKey())
							.build()
			);
			mediaRepository.deleteById(id);
		} catch (MinioException | InvalidKeyException | IOException | NoSuchAlgorithmException e) {
			throw new RuntimeException("Failed to delete media", e);
		}
	}

	@Transactional(readOnly = true)
	public Page<MediaMetadataResponse> list(Pageable pageable) {
		return mediaRepository.findAll(pageable)
				.map(m -> new MediaMetadataResponse(
						m.getId(),
						m.getBucket(),
						m.getObjectKey(),
						m.getContentType(),
						m.getSizeBytes(),
						m.getCreatedAt(),
						m.getUploadedBy(),
						"/media/" + m.getId() + "/download"
				));
	}

	@Transactional(readOnly = true)
	public PresignResponse presign(UUID id, int expiresSeconds) {
		Media media = mediaRepository.findById(id)
				.orElseThrow(() -> new MediaNotFoundException(id));
		try {
			String url = minioClient.getPresignedObjectUrl(
					GetPresignedObjectUrlArgs.builder()
							.method(Method.GET)
							.bucket(media.getBucket())
							.object(media.getObjectKey())
							.expiry(expiresSeconds)
							.build()
			);
			Instant expiresAt = Instant.now().plusSeconds(expiresSeconds);
			return new PresignResponse(url, expiresAt);
		} catch (MinioException e) {
			throw new RuntimeException("Failed to generate presigned URL", e);
		} catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
			throw new RuntimeException(e);
		}
	}

	public static class MediaNotFoundException extends RuntimeException {
		public MediaNotFoundException(UUID id) {
			super("Media not found: " + id);
		}
	}
}

