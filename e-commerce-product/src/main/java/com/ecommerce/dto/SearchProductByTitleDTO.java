package com.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchProductByTitleDTO {
    private String title;
    private Integer pageSize;
    private Integer pageNum;
}
