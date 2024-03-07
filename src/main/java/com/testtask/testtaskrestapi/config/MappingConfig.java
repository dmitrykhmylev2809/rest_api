package com.testtask.testtaskrestapi.config;

import com.testtask.testtaskrestapi.mapper.NewsMapper;
import com.testtask.testtaskrestapi.mapper.NewsTypeMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfig {

    @Bean
    public NewsMapper newsMapper() {
        return Mappers.getMapper(NewsMapper.class);
    }

    @Bean
    public NewsTypeMapper newsTypeMapper() {
        return Mappers.getMapper(NewsTypeMapper.class);
    }

}