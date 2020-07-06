package com.ecommerce.vojo.image;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchProductImageVO {
    private Integer manId;
    private String title;
    private Integer pageNum;
    private Integer pageSize;
}
