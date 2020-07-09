package com.ecommerce.vojo.company;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CompanyEntryVO {
    @ApiModelProperty(value = "主键，制造商ID")
    private Integer manId;

    /**
     * 制造商英文名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "制造商英文名")
    private String nameEn;

    /**
     * 制造商中文名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "制造商中文名")
    private String nameCn;

    /**
     * 品牌商认证类型 1-TUV,2-UL
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "品牌商认证类型 1-TUV,2-UL")
    private String gmcReportType;

    /**
     * 证书地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "证书地址")
    private String gmcReportUrl;
}
