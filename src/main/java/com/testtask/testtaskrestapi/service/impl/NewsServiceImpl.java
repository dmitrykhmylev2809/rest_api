package com.testtask.testtaskrestapi.service.impl;

import com.testtask.testtaskrestapi.dto.NewsDTO;
import com.testtask.testtaskrestapi.entity.News;
import com.testtask.testtaskrestapi.exception.NotFoundException;
import com.testtask.testtaskrestapi.mapper.NewsMapper;
import com.testtask.testtaskrestapi.repository.NewsRepository;
import com.testtask.testtaskrestapi.repository.NewsTypeRepository;
import com.testtask.testtaskrestapi.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsTypeRepository newsTypeRepository;
    private final NewsMapper newsMapper;

    public List<NewsDTO> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::newsToNewsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NewsDTO> getNewsById(Long id) {
        return newsRepository.findById(id).map(newsMapper::newsToNewsDTO);
    }

    @Override
    public NewsDTO createNews(NewsDTO newsDTO) {
        Long newsTypeId = newsDTO.getNewsTypeId();
        if (newsTypeId != null && !newsTypeRepository.existsById(newsTypeId)) {
            throw new NotFoundException("NewsType with id " + newsTypeId + " not found");
        }
        News news = newsMapper.newsDTOToNews(newsDTO);
        return newsMapper.newsToNewsDTO(newsRepository.save(news));
    }

    @Override
    public NewsDTO updateNews(Long id, NewsDTO newsDTO) {
        return newsMapper.newsToNewsDTO(newsRepository.save(newsMapper.newsDTOToNews(newsDTO)));
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public List<NewsDTO> getNewsByTypeId(Long typeId) {
        return newsRepository.findAllByNewsType_Id(typeId).stream()
                .map(newsMapper::newsToNewsDTO)
                .collect(Collectors.toList());
    }
}

