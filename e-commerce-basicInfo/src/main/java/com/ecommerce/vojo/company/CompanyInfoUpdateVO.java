package com.ecommerce.vojo.company;

import com.ecommerce.common.validationGroup.InsertGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
public class CompanyInfoUpdateVO {

    @NotNull(message = "制造商ID不能为空！", groups = {InsertGroup.class})
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

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date creationDate;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新人")
    private String lastUpdateBy;

    /**
     * 更新时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新时间")
    private Date lastUpdateDate;

    /**
     * 乐观锁
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "乐观锁")
    private Integer callCnt;

    /**
     * 备注
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态")
    private String stsCd;

    /**
     * 品牌商公司介绍
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "品牌商公司介绍")
    private String description;
}
