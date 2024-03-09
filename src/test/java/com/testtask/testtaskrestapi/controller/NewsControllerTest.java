package com.testtask.testtaskrestapi.controller;

import com.testtask.testtaskrestapi.dto.NewsDTO;
import com.testtask.testtaskrestapi.mapper.NewsMapper;
import com.testtask.testtaskrestapi.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NewsController.class)
public class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsService newsService;

    @MockBean
    private NewsMapper newsMapper;

    @Test
    public void testGetAllNews() throws Exception {
        when(newsService.getAllNews()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/news"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetNewsById() throws Exception {
        Long newsId = 1L;
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(newsId);
        when(newsService.getNewsById(newsId)).thenReturn(Optional.of(newsDTO));
        mockMvc.perform(get("/news/{id}", newsId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(newsId));
    }

    @Test
    public void testCreateNews() throws Exception {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(1L);
        when(newsService.createNews(any(NewsDTO.class))).thenReturn(newsDTO);
        mockMvc.perform(post("/news")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Test News\",\"shortDescription\":\"Test Description\",\"fullDescription\":\"Test Full Description\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }
    @Test
    public void testUpdateNews() throws Exception {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(1L);
        newsDTO.setName("News");
        newsDTO.setShortDescription("Short Description");
        newsDTO.setFullDescription("Full Description");
        newsDTO.setNewsTypeId(2L);
        when(newsService.updateNews(1L, newsDTO)).thenReturn(newsDTO);
        mockMvc.perform(put("/news/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"News\",\"shortDescription\":\"Short Description\",\"fullDescription\":\"Full Description\", \"newsTypeId\": \"2\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("News"))
                .andExpect(jsonPath("$.shortDescription").value("Short Description"))
                .andExpect(jsonPath("$.fullDescription").value("Full Description"))
                .andExpect(jsonPath("$.newsTypeId").value(2L));
    }


    @Test
    public void testDeleteNews() throws Exception {
        Long newsId = 1L;
        mockMvc.perform(delete("/news/{id}", newsId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetNewsByTypeId() throws Exception {
        Long typeId = 1L;
        when(newsService.getNewsByTypeId(typeId)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/news/type/{typeId}", typeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}
