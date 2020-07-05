package com.ecommerce.vojo;

import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 钱包账户VO
 * Created by yousabla on 2020/7/3.
 */

@Api("钱包账户VO")
@Data
public class WalletAccountVO implements Serializable {

    @ApiModelProperty("用户名")
    @NotBlank(message = "username cannot be blank")
    private String accountName;

    @ApiModelProperty("密码")
    @NotBlank(message = "password cannot be blank")
    private String password;

    @ApiModelProperty("邮箱")
    @Email(message = "email format wrong",groups = {InsertGroup.class})
    @NotBlank(message = "email cannot be blank",groups = {InsertGroup.class})
    private String email;

//    @ApiModelProperty(value = "卖家id")
//    private Integer buyerId;
//
//    @ApiModelProperty(value = "可用金额")
//    private BigDecimal availableMoney;
//
//    @ApiModelProperty(value = "充值中金额")
//    private BigDecimal depositingMoney;
//
//    @ApiModelProperty(value = "提现中金额")
//    private BigDecimal withdrawingMoney;

//    @ApiModelProperty(value = "主键")
//    private Integer buyerId;
//
//    @ApiModelProperty(value = "激活时间")
//    private Date activeTime;
//
//    @ApiModelProperty(value = "是否激活 N - 未激活 , Y - 激活")
//    private String isActive;

//    @NotBlank(groups = {InsertGroup.class})
//    @ApiModelProperty(value = "状态  7 -正常,  17 -冻结")
//    private Byte status;

//    @NotBlank(groups = {InsertGroup.class})
//    @ApiModelProperty(value = "创建人")
//    private String createBy;

//    @NotBlank(groups = {InsertGroup.class})
//    @ApiModelProperty(value = "创建时间")
//    private Date createTime;
    //TODO
//    @NotBlank(groups = {UpdateGroup.class})
//    @ApiModelProperty(value = "更新人")
//    private String lastUpdateBy;

//    @NotBlank(groups = {UpdateGroup.class})
//    @ApiModelProperty(value = "最后更新时间")
//    private Date lastUpdateTime;

//    @NotNull(groups = {InsertGroup.class})
//    @ApiModelProperty(value = "账户类型:1-客户 2-厂家")
//    private Integer accountType;

//    @NotBlank(message = "please confirm hold order time",groups = {InsertGroup.class})
//    @ApiModelProperty(value = "订单处理时间")
//    private String holdOrderTime;
//
//    @NotBlank(message = "please confirm auto pay status",groups = {InsertGroup.class})
//    @ApiModelProperty(value = "自动支付状态 0-FALSE 1-TRUE")
//    private String autoPayStatus;
//
//    @ApiModelProperty(value = "账户币种：USD,RMB")
//    private String currency;

}
