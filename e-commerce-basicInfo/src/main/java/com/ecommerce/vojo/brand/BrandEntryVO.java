package com.ecommerce.vojo.brand;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BrandEntryVO {

    /**
     * 公司英文名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "公司英文名")
    private String nameEn;

    /**
     * 备注，用它存logo地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
