package com.hedgefo9.libraryapp.userservice.exception;

import java.time.OffsetDateTime;

public record ApiErrorRespsonse(
        OffsetDateTime timestamp,
        int status,
        String message,
        String path
) {}
