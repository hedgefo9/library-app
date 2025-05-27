package com.hedgefo9.libraryapp.userservice.event;

import java.util.UUID;

public record UserDeletedEvent(
        UUID userId
) {
}
