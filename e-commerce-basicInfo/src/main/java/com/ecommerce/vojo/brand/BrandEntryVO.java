package com.ecommerce.vojo.brand;

import com.ecommerce.common.validationGroup.InsertGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class BrandEntryVO {

    @ApiModelProperty(value = " 主键")
    private Integer brdId;

    @ApiModelProperty(value = "公司信息，外键关联man表主键")
    private Integer manId;

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
