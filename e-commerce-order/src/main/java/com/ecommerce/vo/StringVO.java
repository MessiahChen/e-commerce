package com.ecommerce.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ProjectName: e-commerce
 * @Package: com.ecommerce.vo
 * @ClassName: StringVO
 * @Description: 接受String类型的json
 * @Author: 邱晓淋
 * @CreateDate: 2020/7/8 11:03
 */
public class StringVO {
    @ApiModelProperty("指代前端往后端传入的String数据，如各种String型的id或者username等")

    String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
