package com.testtask.testtaskrestapi.controller;

import com.testtask.testtaskrestapi.dto.NewsTypeDTO;
import com.testtask.testtaskrestapi.mapper.NewsTypeMapper;
import com.testtask.testtaskrestapi.service.NewsTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news-types")
@RequiredArgsConstructor
public class NewsTypeController {

    private final NewsTypeService newsTypeService;
    private final NewsTypeMapper newsTypeMapper;

    @PostMapping
    public ResponseEntity<NewsTypeDTO> createNewsType(@RequestBody NewsTypeDTO newsTypeDTO) {
        NewsTypeDTO createdNewsTypeDTO = newsTypeService.createNewsType(newsTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNewsTypeDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<NewsTypeDTO> getNewsTypeById(@PathVariable Long id) {
        Optional<NewsTypeDTO> newsTypeDTOOptional = newsTypeService.getNewsTypeById(id);
        return newsTypeDTOOptional
                .map(newsTypeDTO -> ResponseEntity.ok().body(newsTypeDTO))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<NewsTypeDTO>> getAllNewsTypes() {
        List<NewsTypeDTO> allNewsTypes = newsTypeService.getAllNewsTypes();
        return ResponseEntity.ok().body(allNewsTypes);
    }


    @PutMapping("/{id}")
    public ResponseEntity<NewsTypeDTO> updateNewsType(@PathVariable Long id, @RequestBody NewsTypeDTO newsTypeDTO) {
        NewsTypeDTO updatedNewsTypeDTO = newsTypeService.updateNewsType(id, newsTypeDTO);
        return ResponseEntity.ok().body(updatedNewsTypeDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNewsType(@PathVariable Long id) {
        newsTypeService.deleteNewsType(id);
        return ResponseEntity.noContent().build();
    }
}