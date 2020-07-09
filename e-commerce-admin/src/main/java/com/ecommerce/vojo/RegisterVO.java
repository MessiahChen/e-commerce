package com.ecommerce.vojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterVO {
    @ApiModelProperty(value = "用户名，唯一")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;


//    @ApiModelProperty(value = "权限")
//    private String rights;

    @ApiModelProperty(value = "昵称（可重复）")
    private String name;

    @ApiModelProperty(value = "角色ID")
    private String roleId;

    @ApiModelProperty(value = "手机")
    private String phone;

}
