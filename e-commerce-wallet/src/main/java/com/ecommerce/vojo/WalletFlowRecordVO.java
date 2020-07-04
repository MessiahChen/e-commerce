package com.ecommerce.vojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class WalletFlowRecordVO {

    @ApiModelProperty(value = "买家ID")
    private Integer buyerId;

    @ApiModelProperty(value = "流水号")
    private String transactionNumber;

    @ApiModelProperty(value = "流水金额")
    private BigDecimal transactionMoney;

    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @ApiModelProperty(value = "状态")
    private Byte status;
}
