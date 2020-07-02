package com.ecommerce.pojo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UllUserLoginLogoutLog implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private Integer ullId;

    /**
     * 用户表主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户表主键")
    private String usiId;

    /**
     * 用户登录token
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户登录token")
    private String token;

    /**
     * 终端版本类别 1-APP 2-web
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "终端版本类别 1-APP 2-web")
    private String terminalType;

    /**
     * 1-login 2-logout
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "1-login 2-logout")
    private String operatingType;

    /**
     * 操作时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "操作时间")
    private Date operatingDate;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建人")
    private String createdBy;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "创建时间")
    private Date creationDate;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改人")
    private String lastUpdateBy;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date lastUpdateDate;

    /**
     * 乐观锁
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "乐观锁")
    private Integer callCnt;

    /**
     * 备注
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态")
    private String stsCd;

    private static final long serialVersionUID = 1L;

    public Integer getUllId() {
        return ullId;
    }

    public void setUllId(Integer ullId) {
        this.ullId = ullId;
    }

    public String getUsiId() {
        return usiId;
    }

    public void setUsiId(String usiId) {
        this.usiId = usiId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getOperatingType() {
        return operatingType;
    }

    public void setOperatingType(String operatingType) {
        this.operatingType = operatingType;
    }

    public Date getOperatingDate() {
        return operatingDate;
    }

    public void setOperatingDate(Date operatingDate) {
        this.operatingDate = operatingDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getCallCnt() {
        return callCnt;
    }

    public void setCallCnt(Integer callCnt) {
        this.callCnt = callCnt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStsCd() {
        return stsCd;
    }

    public void setStsCd(String stsCd) {
        this.stsCd = stsCd;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ullId=").append(ullId);
        sb.append(", usiId=").append(usiId);
        sb.append(", token=").append(token);
        sb.append(", terminalType=").append(terminalType);
        sb.append(", operatingType=").append(operatingType);
        sb.append(", operatingDate=").append(operatingDate);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", creationDate=").append(creationDate);
        sb.append(", lastUpdateBy=").append(lastUpdateBy);
        sb.append(", lastUpdateDate=").append(lastUpdateDate);
        sb.append(", callCnt=").append(callCnt);
        sb.append(", remark=").append(remark);
        sb.append(", stsCd=").append(stsCd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}