package com.ecommerce.pojo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class SysUser implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "主键")
    private String userId;

    /**
     * 用户名，唯一
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "用户名，唯一")
    private String username;

    /**
     * 密码
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 昵称（可重复）
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "昵称（可重复）")
    private String name;

    /**
     * 权限
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "权限")
    private String rights;

    /**
     * 角色ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "角色ID")
    private String roleId;

    /**
     * 上次登录时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "上次登录时间")
    private String lastLogin;

    /**
     * 登录IP
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "登录IP")
    private String ip;

    /**
     * 状态
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态")
    private String status;

    /**
     * 备注
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "备注")
    private String bz;

    /**
     * 皮肤样式
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "皮肤样式")
    private String skin;

    /**
     * 邮箱
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 固话号码
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "固话号码")
    private Integer number;

    /**
     * 手机
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "手机")
    private String phone;

    /**
     * 产商,借卖方ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "产商,借卖方ID")
    private Integer manBuyerId;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getManBuyerId() {
        return manBuyerId;
    }

    public void setManBuyerId(Integer manBuyerId) {
        this.manBuyerId = manBuyerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", name=").append(name);
        sb.append(", rights=").append(rights);
        sb.append(", roleId=").append(roleId);
        sb.append(", lastLogin=").append(lastLogin);
        sb.append(", ip=").append(ip);
        sb.append(", status=").append(status);
        sb.append(", bz=").append(bz);
        sb.append(", skin=").append(skin);
        sb.append(", email=").append(email);
        sb.append(", number=").append(number);
        sb.append(", phone=").append(phone);
        sb.append(", manBuyerId=").append(manBuyerId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}