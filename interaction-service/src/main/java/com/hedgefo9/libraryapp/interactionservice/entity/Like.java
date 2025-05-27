package com.hedgefo9.libraryapp.interactionservice.entity;

import com.hedgefo9.libraryapp.interactionservice.entity.id.LikeId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "likes")
@Entity
@IdClass(LikeId.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Like {
    @Id
    private Long bookId;
    @Id
    private Long userId;
}

