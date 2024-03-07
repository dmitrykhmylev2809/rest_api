package com.testtask.testtaskrestapi.repository;

import com.testtask.testtaskrestapi.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findAllByNewsType_Id(Long typeId);
}
