package com.ecommerce.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ProjectName: e-commerce
 * @Package: com.ecommerce.vo
 * @ClassName: IntegerVO
 * @Description: 接受Integer类型的Vo
 * @Author: 邱晓淋
 * @CreateDate: 2020/7/8 11:04
 */
public class IntegerVO {

    @ApiModelProperty("指代前端往后端传入的整数数据，如各种id")
    int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
