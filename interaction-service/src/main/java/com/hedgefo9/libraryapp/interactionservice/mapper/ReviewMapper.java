package com.hedgefo9.libraryapp.interactionservice.mapper;

import com.hedgefo9.libraryapp.interactionservice.dto.ReviewDto;
import com.hedgefo9.libraryapp.interactionservice.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ReviewMapper {
	public abstract Review dtoToEntity(ReviewDto dto);

	public abstract ReviewDto entityToDto(Review entity);

	public abstract List<ReviewDto> entityListToDtoList(List<Review> entities);

	public abstract Review updateEntityFromDto(ReviewDto dto, @MappingTarget Review entity);
}
