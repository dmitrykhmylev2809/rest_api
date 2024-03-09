package com.testtask.testtaskrestapi.service.impl;

import com.testtask.testtaskrestapi.dto.NewsDTO;
import com.testtask.testtaskrestapi.entity.News;
import com.testtask.testtaskrestapi.entity.NewsType;
import com.testtask.testtaskrestapi.mapper.NewsMapper;
import com.testtask.testtaskrestapi.repository.NewsRepository;
import com.testtask.testtaskrestapi.repository.NewsTypeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class NewsServiceImplTest {

    @InjectMocks
    private NewsServiceImpl newsService;

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private NewsTypeRepository newsTypeRepository;

    @Mock
    private NewsMapper newsMapper;

    @Test
    public void testGetAllNews() {
        News news1 = new News();
        NewsType newsType1 = new NewsType();
        newsType1.setId(1L);
        newsType1.setName("newType_name_1");
        newsType1.setColor("color_1");
        news1.setId(1L);
        news1.setName("news_1");
        news1.setShortDescription("short_1");
        news1.setFullDescription("full_1");
        news1.setNewsType(newsType1);

        News news2 = new News();
        NewsType newsType2 = new NewsType();
        newsType2.setId(2L);
        newsType2.setName("newType_name_2");
        newsType2.setColor("color_2");
        news2.setId(2L);
        news2.setName("news_2");
        news2.setShortDescription("short_2");
        news2.setFullDescription("full_2");
        news2.setNewsType(newsType2);

        News news3 = new News();
        news3.setId(3L);
        news3.setName("news_3");
        news3.setShortDescription("short_3");
        news3.setFullDescription("full_3");
        news3.setNewsType(newsType1);

        List<News> newsList = new ArrayList<>();

        newsList.add(news1);
        newsList.add(news2);
        newsList.add(news3);
        when(newsRepository.findAll()).thenReturn(newsList);

        List<NewsDTO> expected = new ArrayList<>();

        NewsDTO newsDto1 = new NewsDTO();

        newsDto1.setId(1L);
        newsDto1.setName("news_1");
        newsDto1.setShortDescription("short_1");
        newsDto1.setFullDescription("full_1");
        newsDto1.setNewsTypeId(1L);

        NewsDTO newsDto2 = new NewsDTO();

        newsDto2.setId(2L);
        newsDto2.setName("news_2");
        newsDto2.setShortDescription("short_2");
        newsDto2.setFullDescription("full_2");
        newsDto2.setNewsTypeId(2L);

        NewsDTO newsDto3 = new NewsDTO();
        newsDto3.setId(3L);
        newsDto3.setName("news_3");
        newsDto3.setShortDescription("short_3");
        newsDto3.setFullDescription("full_3");
        newsDto3.setNewsTypeId(1L);

        expected.add(newsDto1);
        expected.add(newsDto2);
        expected.add(newsDto3);

        when(newsMapper.newsToNewsDTO(news1)).thenReturn(newsDto1);
        when(newsMapper.newsToNewsDTO(news2)).thenReturn(newsDto2);
        when(newsMapper.newsToNewsDTO(news3)).thenReturn(newsDto3);

        List<NewsDTO> result = newsService.getAllNews();

        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void testGetNewsById() {
        Long id = 1L;
        News news = new News();
        NewsType newsType = new NewsType();
        newsType.setId(1L);
        newsType.setName("newType_name_1");
        newsType.setColor("color_1");
        news.setId(id);
        news.setName("news_1");
        news.setShortDescription("short_1");
        news.setFullDescription("full_1");
        news.setNewsType(newsType);

        when(newsRepository.findById(id)).thenReturn(Optional.of(news));

        NewsDTO expected = new NewsDTO();
        expected.setId(id);
        expected.setName("news_1");
        expected.setShortDescription("short_1");
        expected.setFullDescription("full_1");
        expected.setNewsTypeId(1L);

        when(newsMapper.newsToNewsDTO(news)).thenReturn(expected);

        Optional<NewsDTO> result = newsService.getNewsById(id);

        assertEquals(Optional.of(expected), result);
    }

    @Test
    public void testCreateNews() {

        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setName("news_1");
        newsDTO.setShortDescription("short_1");
        newsDTO.setFullDescription("full_1");
        newsDTO.setNewsTypeId(1L);

        NewsType newsType = new NewsType();
        newsType.setId(1L);
        newsType.setName("newType_name_1");
        newsType.setColor("color_1");

        when(newsTypeRepository.existsById(1L)).thenReturn(true);

        News createdNews = new News();
        createdNews.setId(1L);
        createdNews.setName("news_1");
        createdNews.setShortDescription("short_1");
        createdNews.setFullDescription("full_1");
        createdNews.setNewsType(newsType);

        when(newsMapper.newsDTOToNews(newsDTO)).thenReturn(createdNews);
        when(newsRepository.save(createdNews)).thenReturn(createdNews);
        when(newsMapper.newsToNewsDTO(createdNews)).thenReturn(newsDTO);

        NewsDTO result = newsService.createNews(newsDTO);

        assertEquals(newsDTO, result);
    }

    @Test
    public void testUpdateNews() {
        Long newsId = 1L;
        NewsDTO newsDTOBeforeUpdate = new NewsDTO();
        newsDTOBeforeUpdate.setId(newsId);
        newsDTOBeforeUpdate.setNewsTypeId(1L);

        NewsDTO newsDTOAfterUpdate = new NewsDTO();
        newsDTOAfterUpdate.setId(newsId);
        newsDTOAfterUpdate.setName("Updated News");
        newsDTOAfterUpdate.setShortDescription("Updated Short Description");
        newsDTOAfterUpdate.setFullDescription("Updated Full Description");
        newsDTOAfterUpdate.setNewsTypeId(1L);

        when(newsService.updateNews(newsId, newsDTOBeforeUpdate)).thenReturn(newsDTOAfterUpdate);

        NewsDTO result = newsService.updateNews(newsId, newsDTOBeforeUpdate);

        assertEquals(newsDTOAfterUpdate, result);
    }

    @Test
    public void testDeleteNews() {
        Long id = 1L;
        News news = new News();
        news.setId(id);

        when(newsRepository.findById(id)).thenReturn(Optional.of(news));

        newsService.deleteNews(id);

        verify(newsRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetNewsByTypeId() {
        Long typeId = 1L;

        NewsType newsType = new NewsType();
        newsType.setId(typeId);
        newsType.setName("newType_name_1");
        newsType.setColor("color_1");

        News news1 = new News();
        news1.setId(1L);
        news1.setName("news_1");
        news1.setShortDescription("short_1");
        news1.setFullDescription("full_1");
        news1.setNewsType(newsType);

        News news2 = new News();
        news2.setId(2L);
        news2.setName("news_2");
        news2.setShortDescription("short_2");
        news2.setFullDescription("full_2");
        news2.setNewsType(newsType);

        List<News> newsList = new ArrayList<>();
        newsList.add(news1);
        newsList.add(news2);

        when(newsRepository.findAllByNewsType_Id(typeId)).thenReturn(newsList);

        NewsDTO newsDto1 = new NewsDTO();
        newsDto1.setId(1L);
        newsDto1.setName("news_1");
        newsDto1.setShortDescription("short_1");
        newsDto1.setFullDescription("full_1");
        newsDto1.setNewsTypeId(typeId);

        NewsDTO newsDto2 = new NewsDTO();
        newsDto2.setId(2L);
        newsDto2.setName("news_2");
        newsDto2.setShortDescription("short_2");
        newsDto2.setFullDescription("full_2");
        newsDto2.setNewsTypeId(typeId);

        List<NewsDTO> expected = new ArrayList<>();
        expected.add(newsDto1);
        expected.add(newsDto2);

        when(newsMapper.newsToNewsDTO(news1)).thenReturn(newsDto1);
        when(newsMapper.newsToNewsDTO(news2)).thenReturn(newsDto2);

        List<NewsDTO> result = newsService.getNewsByTypeId(typeId);

        assertEquals(expected, result);
    }
}
