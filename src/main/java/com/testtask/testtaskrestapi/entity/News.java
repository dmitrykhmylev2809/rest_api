package com.testtask.testtaskrestapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDescription;
    private String fullDescription;

    @ManyToOne
    @JoinColumn(name = "news_type_id")
    private NewsType newsType;

}