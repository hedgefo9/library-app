package com.hedgefo9.libraryapp.bookservice.repository;

import com.hedgefo9.libraryapp.bookservice.entity.Author;
import com.hedgefo9.libraryapp.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorsContains(Author author);
}
