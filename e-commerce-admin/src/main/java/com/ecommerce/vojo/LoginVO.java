package com.ecommerce.vojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginVO {
    @ApiModelProperty(value = "用户名，唯一")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "验证码随机Token")
    private String tokenId;
}
