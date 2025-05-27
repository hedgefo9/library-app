package com.hedgefo9.libraryapp.userservice.event;

import java.util.UUID;

public record UserUpdatedEvent(UUID userId,
                               String email,
                               String password,
                               String[] roles) {
}
