package com.hedgefo9.libraryapp.bookservice.mapper;

import com.hedgefo9.libraryapp.bookservice.dto.BookDto;
import com.hedgefo9.libraryapp.bookservice.entity.Book;
import com.hedgefo9.libraryapp.events.BookService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {AuthorMapper.class})
public interface BookMapper {
    @Mapping(target = "authorIds", source = "authors")
    BookDto entityToDto(Book book);

    @Mapping(target = "authors", source = "authorIds")
    Book dtoToEntity(BookDto bookDto);

    @Mapping(target = "authors", source = "authorIds")
    Book updateFromDto(BookDto bookDto, @MappingTarget Book book);

    @Mapping(target = "authors", source = "authorIds")
    List<BookDto> toDtoList(List<Book> books);

    @Mapping(target = "authorsList", source = "authors")
    BookService.Book entityToProtoMessage(Book entity);

    @Mapping(target = "authors", source = "authorsList")
    Book protoMessageToEntity(BookService.Book message);
}