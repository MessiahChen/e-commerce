package com.ecommerce.vojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddMenuVO {

    /**
     * 父级ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "父级ID")
    private Long parentId;

    /**
     * 菜单名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "菜单名称")
    private String title;

    /**
     * 菜单级数
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "菜单级数")
    private Integer level;

    /**
     * 菜单排序
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "菜单排序")
    private Integer sort;

    /**
     * 前端名称
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "前端名称")
    private String name;

    /**
     * 前端图标
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "前端图标")
    private String icon;

    /**
     * 前端隐藏
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "前端隐藏")
    private Integer hidden;
}
