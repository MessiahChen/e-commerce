package com.ecommerce.vojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by yousabla on 2020/7/3.
 * 用于提现/充值的VO类
 */

@Api("用于提现/充值的VO类")
@Data
public class WalletBalanceVO {

    @ApiModelProperty(value = "卖家id")
    private Integer buyerId;

    @ApiModelProperty(value = "可用金额")
    private BigDecimal availableMoney;

    @ApiModelProperty(value = "充值中金额")
    private BigDecimal depositingMoney;

    @ApiModelProperty(value = "提现中金额")
    private BigDecimal withdrawingMoney;

//    @ApiModelProperty(value = "创建人")
//    private String createBy;
//
//    @ApiModelProperty(value = "创建时间")
//    private Date createTime;
//
//    @ApiModelProperty(value = "更新人")
//    private String lastUpdateBy;
//
//    @ApiModelProperty(value = "最后更新时间")
//    private Date lastUpdateTime;

    @ApiModelProperty(value = "账户币种：USD,RMB")
    private String currency;
}
