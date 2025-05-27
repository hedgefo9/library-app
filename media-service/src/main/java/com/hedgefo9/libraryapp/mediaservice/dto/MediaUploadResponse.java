package com.hedgefo9.libraryapp.mediaservice.dto;

import java.util.UUID;

public record MediaUploadResponse(UUID id, String url, String bucket) {
}
