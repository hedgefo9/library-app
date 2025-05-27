package com.hedgefo9.libraryapp.bookservice.mapper;

import com.hedgefo9.libraryapp.bookservice.dto.BookDto;
import com.hedgefo9.libraryapp.bookservice.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {
    BookDto toBookDto(Book book);
    Book toBook(BookDto bookDto);
    Book updateFromDto(BookDto bookDto, @MappingTarget Book book);
}