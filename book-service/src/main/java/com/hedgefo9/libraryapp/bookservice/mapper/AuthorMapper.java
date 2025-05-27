package com.hedgefo9.libraryapp.bookservice.mapper;

import com.hedgefo9.libraryapp.bookservice.dto.AuthorDto;
import com.hedgefo9.libraryapp.bookservice.entity.Author;
import com.hedgefo9.libraryapp.bookservice.repository.AuthorRepository;
import com.hedgefo9.libraryapp.events.BookService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class AuthorMapper {
    @Autowired
    protected AuthorRepository authorRepository;

    public abstract AuthorDto entityToDto(Author author);

    public abstract Author dtoToEntity(AuthorDto authorDto);

    public abstract Author updateEntityFromDto(AuthorDto authorDto, @MappingTarget Author author);

    public abstract List<AuthorDto> entityListToDtoList(List<Author> authorList);

    @Transactional(readOnly = true)
    public List<Author> map(Long[] authorIds) {
        if (authorIds == null || authorIds.length == 0) {
            return List.of();
        }
        return authorRepository.findAllById(Arrays.asList(authorIds));
    }

    public Long[] map(List<Author> authors) {
        if (authors == null || authors.isEmpty()) {
            return new Long[0];
        }
        return authors.stream().map(Author::getId).toArray(Long[]::new);
    }

    public abstract BookService.Author entityToProtoMessage(Author entity);

    public abstract Author protoMessageToEntity(BookService.Author message);
}
