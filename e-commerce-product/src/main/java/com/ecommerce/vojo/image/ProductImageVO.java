package com.ecommerce.vojo.image;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductImageVO {
    @Override
    public String toString() {
        return "ProductImageVO{" +
                "proId=" + proId +
                ", title='" + title + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", imageUri='" + imageUri + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 主键id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键id")
    private Integer proId;

    /**
     * 标题
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 主分类名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主分类名称")
    private String categoryName;

    /**
     * 主图
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主图URI")
    private String imageUri;

    /**
     * 状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "商品状态")
    private String status;

}

