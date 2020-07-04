package com.ecommerce.vojo.entry;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchProductVO {
    private Integer manId;
    private String title;
    private Integer pageNum;
    private Integer pageSize;
}
