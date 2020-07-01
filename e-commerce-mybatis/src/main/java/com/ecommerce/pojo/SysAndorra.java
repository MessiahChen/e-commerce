package com.ecommerce.pojo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class SysAndorra implements Serializable {
    /**
     * ID
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 标题
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 类型
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "类型")
    private String type;

    /**
     * 图片地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "图片地址")
    private String adurl;

    /**
     * 发布人
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "发布人")
    private String publisher;

    /**
     * 添加时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "添加时间")
    private Date addtime;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "修改时间")
    private Date uptime;

    /**
     * 开始时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "开始时间")
    private String starttime;

    /**
     * 结束时间
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "结束时间")
    private String endtime;

    /**
     * 状态，0位默认未启动，1为启动
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "状态，0位默认未启动，1为启动")
    private String status;

    /**
     * 跳转URL地址
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "跳转URL地址")
    private String tourl;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdurl() {
        return adurl;
    }

    public void setAdurl(String adurl) {
        this.adurl = adurl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUptime() {
        return uptime;
    }

    public void setUptime(Date uptime) {
        this.uptime = uptime;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTourl() {
        return tourl;
    }

    public void setTourl(String tourl) {
        this.tourl = tourl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", type=").append(type);
        sb.append(", adurl=").append(adurl);
        sb.append(", publisher=").append(publisher);
        sb.append(", addtime=").append(addtime);
        sb.append(", uptime=").append(uptime);
        sb.append(", starttime=").append(starttime);
        sb.append(", endtime=").append(endtime);
        sb.append(", status=").append(status);
        sb.append(", tourl=").append(tourl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}