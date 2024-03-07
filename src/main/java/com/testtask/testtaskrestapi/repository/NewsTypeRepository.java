package com.testtask.testtaskrestapi.repository;

import com.testtask.testtaskrestapi.entity.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsTypeRepository extends JpaRepository<NewsType, Long> {
}

