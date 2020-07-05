package com.ecommerce.vojo.image;

import com.ecommerce.common.validationGroup.InsertGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class ProductCategoryAddVO {

    @NotNull(message = "商品ID不能为空！", groups = {InsertGroup.class})
    @ApiModelProperty(value = "主键id")
    private Integer proId;

    @NotNull(message = "创建人ID不能为空！", groups = {InsertGroup.class})
    @ApiModelProperty(value = "创建人id")
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
