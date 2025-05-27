package com.hedgefo9.libraryapp.searchservice.repository;

import com.hedgefo9.libraryapp.searchservice.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookRepository extends ElasticsearchRepository<Book, Long> {

	@Query("""
    {
      "bool": {
        "should": [
          { "match": { "title": "?0" } },
          { "match": { "description": "?0" } },
          { "nested": {
              "path": "authors",
              "query": { "match": { "authors.name": "?0" } }
            }
          }
        ]
      }
    }
    """)
	Page<Book> searchByTitleDescriptionOrAuthorName(String query, Pageable pageable);
}