package com.testtask.testtaskrestapi.service;


import com.testtask.testtaskrestapi.dto.NewsTypeDTO;
import java.util.List;
import java.util.Optional;

public interface NewsTypeService {

    NewsTypeDTO createNewsType(NewsTypeDTO newsTypeDTO);

    Optional<NewsTypeDTO> getNewsTypeById(Long id);
    List<NewsTypeDTO> getAllNewsTypes();
    void deleteNewsType(Long id);
    NewsTypeDTO updateNewsType(Long id, NewsTypeDTO newsTypeDTO);
}