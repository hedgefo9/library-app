package com.hedgefo9.libraryapp.interactionservice.entity;

import com.hedgefo9.libraryapp.interactionservice.entity.id.NoteId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "notes")
@Entity
@IdClass(NoteId.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
    @Id
    private Long bookId;
    @Id
    private Long userId;

    private String content;
}
