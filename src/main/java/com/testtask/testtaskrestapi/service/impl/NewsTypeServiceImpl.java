package com.testtask.testtaskrestapi.service.impl;

import com.testtask.testtaskrestapi.dto.NewsTypeDTO;
import com.testtask.testtaskrestapi.entity.NewsType;
import com.testtask.testtaskrestapi.exception.NotFoundException;
import com.testtask.testtaskrestapi.mapper.NewsTypeMapper;
import com.testtask.testtaskrestapi.repository.NewsTypeRepository;
import com.testtask.testtaskrestapi.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsTypeServiceImpl implements NewsTypeService {

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Autowired
    private NewsTypeMapper newsTypeMapper;

    @Override
    public List<NewsTypeDTO> getAllNewsTypes() {
        List<NewsType> newsTypes = newsTypeRepository.findAll();
        return newsTypes.stream()
                .map(newsTypeMapper::newsTypeToNewsTypeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<NewsTypeDTO> getNewsTypeById(Long id) {
        Optional<NewsType> newsTypeOptional = newsTypeRepository.findById(id);
        return newsTypeOptional.map(newsTypeMapper::newsTypeToNewsTypeDTO);
    }

    @Override
    public NewsTypeDTO createNewsType(NewsTypeDTO newsTypeDTO) {
        NewsType newsType = newsTypeMapper.newsTypeDTOToNewsType(newsTypeDTO);
        NewsType savedNewsType = newsTypeRepository.save(newsType);
        return newsTypeMapper.newsTypeToNewsTypeDTO(savedNewsType);
    }

    @Override
    public NewsTypeDTO updateNewsType(Long id, NewsTypeDTO newsTypeDTO) {
        Optional<NewsType> newsTypeOptional = newsTypeRepository.findById(id);
        if (newsTypeOptional.isPresent()) {
            NewsType existingNewsType = newsTypeOptional.get();
            newsTypeMapper.updateNewsTypeFromDTO(newsTypeDTO, existingNewsType);
            NewsType savedNewsType = newsTypeRepository.save(existingNewsType);
            return newsTypeMapper.newsTypeToNewsTypeDTO(savedNewsType);
        } else {
            throw new NotFoundException("Невозможно обновить тип новостей");
        }
    }


    @Override
    public void deleteNewsType(Long id) {
        newsTypeRepository.deleteById(id);
    }
}
