package com.testtask.testtaskrestapi.repository;

import com.testtask.testtaskrestapi.entity.News;
import com.testtask.testtaskrestapi.entity.NewsType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class NewsRepositoryTest {


    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12.3");

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.jpa.generate-ddl",()-> true);
    }


    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Test
    void testFindAllByNewsTypeId() {
        NewsType newsType = new NewsType();
        newsType.setId(1L);
        newsType.setName("Test News Type");
        newsTypeRepository.save(newsType);
        News news = new News();
        news.setName("Test News");
        news.setShortDescription("Short Description");
        news.setFullDescription("Full Description");
        news.setNewsType(newsType);
        newsRepository.save(news);

        List<News> newsList = newsRepository.findAllByNewsType_Id(1L);

        Assertions.assertNotNull(newsList);
        Assertions.assertFalse(newsList.isEmpty());
        Assertions.assertEquals(1, newsList.size());
    }
}
