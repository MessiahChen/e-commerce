package com.ecommerce.pojo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WaaWalletAccountFund implements Serializable {
    /**
     * 卖家id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "卖家id")
    private Integer buyerId;

    /**
     * 可用金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "可用金额")
    private BigDecimal availableMoney;

    /**
     * 充值中金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "充值中金额")
    private BigDecimal depositingMoney;

    /**
     * 提现中金额
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "提现中金额")
    private BigDecimal withdrawingMoney;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createBy;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "更新人")
    private String lastUpdateBy;

    /**
     * 最后更新时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "最后更新时间")
    private Date lastUpdateTime;

    /**
     * 账户币种：USD,RMB
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "账户币种：USD,RMB")
    private String currency;

    private static final long serialVersionUID = 1L;

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public BigDecimal getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(BigDecimal availableMoney) {
        this.availableMoney = availableMoney;
    }

    public BigDecimal getDepositingMoney() {
        return depositingMoney;
    }

    public void setDepositingMoney(BigDecimal depositingMoney) {
        this.depositingMoney = depositingMoney;
    }

    public BigDecimal getWithdrawingMoney() {
        return withdrawingMoney;
    }

    public void setWithdrawingMoney(BigDecimal withdrawingMoney) {
        this.withdrawingMoney = withdrawingMoney;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", buyerId=").append(buyerId);
        sb.append(", availableMoney=").append(availableMoney);
        sb.append(", depositingMoney=").append(depositingMoney);
        sb.append(", withdrawingMoney=").append(withdrawingMoney);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateBy=").append(lastUpdateBy);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", currency=").append(currency);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}