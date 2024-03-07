package com.testtask.testtaskrestapi.mapper;

import com.testtask.testtaskrestapi.dto.NewsTypeDTO;
import com.testtask.testtaskrestapi.entity.NewsType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewsTypeMapper {

    NewsTypeMapper INSTANCE = Mappers.getMapper(NewsTypeMapper.class);

    @Mapping(target = "id", ignore = true)
    NewsType newsTypeDTOToNewsType(NewsTypeDTO newsTypeDTO);

    NewsTypeDTO newsTypeToNewsTypeDTO(NewsType newsType);

    @Mapping(target = "id", ignore = true)
    void updateNewsTypeFromDTO(NewsTypeDTO newsTypeDTO, @MappingTarget NewsType newsType);
}