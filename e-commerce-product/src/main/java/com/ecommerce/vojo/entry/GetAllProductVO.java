package com.ecommerce.vojo.entry;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetAllProductVO {
    private Integer manId;
    private Integer pageNum;
    private Integer pageSize;
}
