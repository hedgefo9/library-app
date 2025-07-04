package com.hedgefo9.libraryapp.mediaservice.repository;

import com.hedgefo9.libraryapp.mediaservice.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MediaRepository extends JpaRepository<Media, UUID> {
}
