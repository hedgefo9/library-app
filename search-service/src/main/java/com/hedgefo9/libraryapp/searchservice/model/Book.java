package com.hedgefo9.libraryapp.searchservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "books")
@Data
public class Book {
	@Id
	private Long id;
	private String title;
	private String description;
	private List<Author> authors;
}
