package com.testtask.testtaskrestapi.mapper;

import com.testtask.testtaskrestapi.dto.NewsDTO;
import com.testtask.testtaskrestapi.entity.News;
import com.testtask.testtaskrestapi.entity.NewsType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(source =  "newsType.id", target = "newsTypeId")
    NewsDTO newsToNewsDTO(News news);

    default NewsType map(Long value) {
        if (value == null) {
            return null;
        }
        NewsType newsType = new NewsType();
        newsType.setId(value);
        return newsType;
    }

    @Mapping(target = "newsType", source = "newsTypeId")
    News newsDTOToNews(NewsDTO newsDTO);

    @Mapping(target = "id", ignore = true)
    News updateNewsFromDTO(NewsDTO newsDTO, @MappingTarget News news);
}
