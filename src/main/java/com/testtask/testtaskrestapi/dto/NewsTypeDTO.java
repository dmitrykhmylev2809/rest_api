package com.testtask.testtaskrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NewsTypeDTO {
    @Schema(description = "ID of the news type")
    private Long id;

    @Schema(description = "Name of the news type")
    private String name;

    @Schema(description = "Color associated with the news type")
    private String color;

}