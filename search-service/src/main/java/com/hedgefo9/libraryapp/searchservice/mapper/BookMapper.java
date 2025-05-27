package com.hedgefo9.libraryapp.searchservice.mapper;

import com.hedgefo9.libraryapp.events.BookService;
import com.hedgefo9.libraryapp.searchservice.model.Author;
import com.hedgefo9.libraryapp.searchservice.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
	@Mapping(target = "authors", source = "authorsList")
	Book fromProto(BookService.Book protoBook);

	@Mapping(target = "authorsList", source = "authors")
	BookService.Book toProto(Book book);

	List<Author> protoAuthorsToAuthors(List<BookService.Book> protoAuthors);
	List<BookService.Book> authorsToProtoAuthors(List<Author> authors);

	Author protoAuthorToAuthor(BookService.Book protoAuthor);
	BookService.Book authorToProtoAuthor(Author author);
}