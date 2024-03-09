package com.testtask.testtaskrestapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testtask.testtaskrestapi.dto.NewsTypeDTO;
import com.testtask.testtaskrestapi.mapper.NewsTypeMapper;
import com.testtask.testtaskrestapi.service.NewsTypeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(NewsTypeController.class)
public class NewsTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsTypeService newsTypeService;

    @MockBean
    private NewsTypeMapper newsTypeMapper;

    @Test
    public void testCreateNewsType() throws Exception {
        NewsTypeDTO newsTypeDTO = new NewsTypeDTO();
        newsTypeDTO.setName("Test News Type");

        NewsTypeDTO createdNewsTypeDTO = new NewsTypeDTO();
        createdNewsTypeDTO.setId(1L);
        createdNewsTypeDTO.setName("Test News Type");

        when(newsTypeService.createNewsType(newsTypeDTO)).thenReturn(createdNewsTypeDTO);

        mockMvc.perform(post("/news-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newsTypeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Test News Type")));
    }

    @Test
    public void testGetNewsTypeById() throws Exception {
        Long id = 1L;

        NewsTypeDTO newsTypeDTO = new NewsTypeDTO();
        newsTypeDTO.setId(id);
        newsTypeDTO.setName("Test News Type");

        when(newsTypeService.getNewsTypeById(id)).thenReturn(Optional.of(newsTypeDTO));

        mockMvc.perform(get("/news-types/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Test News Type")));
    }

    @Test
    public void testGetAllNewsTypes() throws Exception {
        List<NewsTypeDTO> newsTypeDTOList = new ArrayList<>();
        NewsTypeDTO newsTypeDTO1 = new NewsTypeDTO();
        newsTypeDTO1.setId(1L);
        newsTypeDTO1.setName("Test News Type 1");
        NewsTypeDTO newsTypeDTO2 = new NewsTypeDTO();
        newsTypeDTO2.setId(2L);
        newsTypeDTO2.setName("Test News Type 2");
        newsTypeDTOList.add(newsTypeDTO1);
        newsTypeDTOList.add(newsTypeDTO2);

        when(newsTypeService.getAllNewsTypes()).thenReturn(newsTypeDTOList);

        mockMvc.perform(get("/news-types"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Test News Type 1")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].name", Matchers.is("Test News Type 2")));
    }

    @Test
    public void testUpdateNewsType() throws Exception {
        Long id = 1L;

        NewsTypeDTO newsTypeDTO = new NewsTypeDTO();
        newsTypeDTO.setId(id);
        newsTypeDTO.setName("Updated Test News Type");

        when(newsTypeService.updateNewsType(id, newsTypeDTO)).thenReturn(newsTypeDTO);

        mockMvc.perform(put("/news-types/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newsTypeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Updated Test News Type")));
    }

    @Test
    public void testDeleteNewsType() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/news-types/{id}", id))
                .andExpect(status().isNoContent());

        verify(newsTypeService, times(1)).deleteNewsType(id);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
