package com.ecommerce.pojo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class WafWalletAccountFund implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Integer buyerId;

    /**
     * 邮箱名
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "邮箱名")
    private String accountName;

    /**
     * 支付密码，加密串
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "支付密码，加密串")
    private String password;

    /**
     * 激活时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "激活时间")
    private Date activeTime;

    /**
     * 是否激活 N - 未激活 , Y - 激活
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "是否激活 N - 未激活 , Y - 激活")
    private String isActive;

    /**
     * 状态  7 -正常,  17 -冻结
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态  7 -正常,  17 -冻结")
    private Byte status;

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
     * 账户类型:1-客户 2-厂家
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "账户类型:1-客户 2-厂家")
    private Integer accountType;

    /**
     * 订单处理时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "订单处理时间")
    private String holdOrderTime;

    /**
     * 0-FALSE 1-TRUE
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "0-FALSE 1-TRUE")
    private String autoPayStatus;

    private static final long serialVersionUID = 1L;

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getHoldOrderTime() {
        return holdOrderTime;
    }

    public void setHoldOrderTime(String holdOrderTime) {
        this.holdOrderTime = holdOrderTime;
    }

    public String getAutoPayStatus() {
        return autoPayStatus;
    }

    public void setAutoPayStatus(String autoPayStatus) {
        this.autoPayStatus = autoPayStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", buyerId=").append(buyerId);
        sb.append(", accountName=").append(accountName);
        sb.append(", password=").append(password);
        sb.append(", activeTime=").append(activeTime);
        sb.append(", isActive=").append(isActive);
        sb.append(", status=").append(status);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastUpdateBy=").append(lastUpdateBy);
        sb.append(", lastUpdateTime=").append(lastUpdateTime);
        sb.append(", accountType=").append(accountType);
        sb.append(", holdOrderTime=").append(holdOrderTime);
        sb.append(", autoPayStatus=").append(autoPayStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}