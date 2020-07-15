package com.ecommerce.vojo.image;

import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class ProductCategoryUpdateVO {
    @NotNull(message = "商品ID不能为空！", groups = {InsertGroup.class})
    @ApiModelProperty(value = "主键id")
    private Integer proId;

    @NotNull(message = "创建人ID不能为空！", groups = {InsertGroup.class})
    @ApiModelProperty(value = "创建人id")
    private String userId;

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Integer> getCategory() {
        return category;
    }

    public void setCategory(List<Integer> category) {
        this.category = category;
    }

    @ApiModelProperty(value = "分类ID")
    private List<Integer> category;
}
