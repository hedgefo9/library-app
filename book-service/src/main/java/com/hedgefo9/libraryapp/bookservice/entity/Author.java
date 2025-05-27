package com.hedgefo9.libraryapp.bookservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    @ToString.Exclude
    private List<Book> books;
}
