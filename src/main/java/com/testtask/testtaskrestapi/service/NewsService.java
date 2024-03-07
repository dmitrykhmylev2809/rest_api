package com.testtask.testtaskrestapi.service;

import com.testtask.testtaskrestapi.dto.NewsDTO;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    List<NewsDTO> getAllNews();
    Optional<NewsDTO> getNewsById(Long id);
    NewsDTO createNews(NewsDTO newsDTO);
    NewsDTO updateNews(Long id, NewsDTO newsDTO);
    void deleteNews(Long id);
    List<NewsDTO> getNewsByTypeId(Long typeId);
}

