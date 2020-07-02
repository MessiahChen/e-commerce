package com.ecommerce.pojo;

import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SysMenu implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    @NotNull(message = "The menuID shouldn't be null", groups = {InsertGroup.class, UpdateGroup.class, SelectGroup.class})
    @Min(value = 10, message = "应该大于10！！", groups = {InsertGroup.class, UpdateGroup.class, SelectGroup.class})
    private Integer menuId;

    /**
     * 菜单名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 路径
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "路径")
    private String menuUrl;

    /**
     * 父类ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "父类ID")
    private String parentId;

    /**
     * 排序标识
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "排序标识")
    private String menuOrder;

    /**
     * 菜单图标样式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "菜单图标样式")
    private String menuIcon;

    /**
     * 菜单类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "菜单类型")
    private String menuType;

    private static final long serialVersionUID = 1L;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(String menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuUrl=").append(menuUrl);
        sb.append(", parentId=").append(parentId);
        sb.append(", menuOrder=").append(menuOrder);
        sb.append(", menuIcon=").append(menuIcon);
        sb.append(", menuType=").append(menuType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}