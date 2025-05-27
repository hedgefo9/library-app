package com.hedgefo9.libraryapp.bookservice.repository;

import com.hedgefo9.libraryapp.bookservice.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
