package com.hedgefo9.libraryapp.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UpdateAuthorRequest {
    private Long id;
    private String name;
    private String bio;
}
