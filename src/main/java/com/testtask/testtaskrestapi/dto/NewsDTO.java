package com.testtask.testtaskrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NewsDTO {
    @Schema(description = "ID of the news")
    private Long id;

    @Schema(description = "Name of the news")
    private String name;

    @Schema(description = "Short description of the news")
    private String shortDescription;

    @Schema(description = "Full description of the news")
    private String fullDescription;

    @Schema(description = "ID of the news type associated with the news")
    private Long newsTypeId;

}
