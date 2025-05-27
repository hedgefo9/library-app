package com.hedgefo9.libraryapp.mediaservice.dto;

import java.time.Instant;
import java.util.UUID;

public record MediaMetadataResponse(UUID id, String bucket, String objectKey, String contentType, Long sizeBytes,
									Instant createdAt, String uploadedBy, String downloadUrl) {
}
