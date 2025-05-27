package com.hedgefo9.libraryapp.mediaservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Media {

	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name = "id", nullable = false, updatable = false)
	private UUID id;

	@Column(name = "bucket", nullable = false)
	private String bucket;

	@Column(name = "object_key", nullable = false, unique = true)
	private String objectKey;

	@Column(name = "content_type", nullable = false)
	private String contentType;

	@Column(name = "size_bytes", nullable = false)
	private Long sizeBytes;

	@Column(name = "created_at", nullable = false, updatable = false)
	private Instant createdAt;

	@Column(name = "uploaded_by", nullable = false)
	private String uploadedBy;

	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
	}
}

