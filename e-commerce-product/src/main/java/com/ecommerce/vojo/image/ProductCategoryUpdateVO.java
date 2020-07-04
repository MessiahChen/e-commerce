package com.ecommerce.vojo.image;

import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class ProductCategoryUpdateVO {
    @NotNull(message = "商品ID不能为空！", groups = {UpdateGroup.class})
    @ApiModelProperty(value = "主键id")
    private Integer proId;

    @NotNull(message = "修改人ID不能为空！", groups = {UpdateGroup.class})
    @ApiModelProperty(value = "修改人id")
    private String userId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "主分类ID")
    private Integer mainCatId;

    @ApiModelProperty(value = "主分类名称")
    private String mainCatName;

    @ApiModelProperty(value = "副分类ID")
    private Integer viceCatId;

    @ApiModelProperty(value = "副分类名称")
    private String viceCatName;
}
