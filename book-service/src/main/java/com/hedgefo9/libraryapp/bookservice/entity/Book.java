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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;

    private String title;

    private String genre;

    private String description;

    private Integer publicationYear;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Author> authors;

    public Book(String isbn, String title, String genre, String description, Integer publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.publicationYear = publicationYear;
    }

    public Book(Long id, String isbn, String title, String genre, String description, Integer publicationYear) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.publicationYear = publicationYear;
    }
}
