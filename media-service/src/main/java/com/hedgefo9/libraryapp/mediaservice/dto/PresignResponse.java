package com.hedgefo9.libraryapp.mediaservice.dto;

import java.time.Instant;

public record PresignResponse(String url, Instant expiresAt) {
}
