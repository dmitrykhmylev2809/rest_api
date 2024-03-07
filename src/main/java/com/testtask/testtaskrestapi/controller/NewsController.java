package com.testtask.testtaskrestapi.controller;

import com.testtask.testtaskrestapi.dto.NewsDTO;
import com.testtask.testtaskrestapi.mapper.NewsMapper;
import com.testtask.testtaskrestapi.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;
    private final NewsMapper newsMapper;

    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAllNews() {
        List<NewsDTO> allNews = newsService.getAllNews();
        return ResponseEntity.ok().body(allNews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id) {
        Optional<NewsDTO> newsDTOOptional = newsService.getNewsById(id);
        return newsDTOOptional
                .map(newsDTO -> ResponseEntity.ok().body(newsDTO))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO newsDTO) {
        NewsDTO createdNewsDTO = newsService.createNews(newsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNewsDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> updateNews(@PathVariable Long id, @RequestBody NewsDTO newsDTO) {
        NewsDTO updatedNewsDTO = newsService.updateNews(id, newsDTO);
        return ResponseEntity.ok().body(updatedNewsDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<NewsDTO>> getNewsByTypeId(@PathVariable Long typeId) {
        List<NewsDTO> newsDTOList = newsService.getNewsByTypeId(typeId);
        return ResponseEntity.ok().body(newsDTOList);
    }
}
