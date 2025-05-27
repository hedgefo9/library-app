package com.hedgefo9.libraryapp.bookservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "books")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class Book {
    // Basic info start
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    private Integer publicationYear;

    private String genre;

    private String title;

    private String description;

    private Double averageRating;

    private Long reviewCount;
    // Basic info end

    private String thumbnailUrl;

    private String contentFileS3Key;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Author> authors;
}
