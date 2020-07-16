package com.ecommerce.vojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchUserVO {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty("页数")
    private Integer pageNum;

    @ApiModelProperty("页面大小")
    private Integer pageSize;
}
