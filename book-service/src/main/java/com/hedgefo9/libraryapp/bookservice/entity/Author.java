package com.hedgefo9.libraryapp.bookservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authors")
@Entity
@ToString
@Data
// @Table(indexes = {@Index(columnList = "name", unique = true)})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String bio;

    private Timestamp createdAt;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    @JsonIgnore
    @ToString.Exclude
    private List<Book> books;

    public Author(String name) {
        this.name = name;
    }

    public Author(Long id, String name, String bio) {
        this.id = id;
        this.name = name;
        this.bio = bio;
    }

    public Author(String name, String bio, Timestamp createdAt) {
        this.name = name;
        this.bio = bio;
        this.createdAt = createdAt;
    }
}
