package com.hedgefo9.libraryapp.interactionservice.mapper;

import com.hedgefo9.libraryapp.interactionservice.dto.NoteDto;
import com.hedgefo9.libraryapp.interactionservice.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class NoteMapper {
	public abstract Note dtoToEntity(NoteDto dto);

	public abstract NoteDto entityToDto(Note entity);

	public abstract List<NoteDto> entityListToDtoList(List<Note> entities);

	public abstract Note updateEntityFromDto(NoteDto dto, @MappingTarget Note entity);
}
