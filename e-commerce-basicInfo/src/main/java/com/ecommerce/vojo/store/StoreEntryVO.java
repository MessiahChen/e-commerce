package com.ecommerce.vojo.store;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StoreEntryVO {
    public Integer getStrId() {
        return strId;
    }

    public void setStrId(Integer strId) {
        this.strId = strId;
    }

    public Integer getDsrId() {
        return dsrId;
    }

    public void setDsrId(Integer dsrId) {
        this.dsrId = dsrId;
    }

    public String getPlatformType() {
        return platformType;
    }

    public void setPlatformType(String platformType) {
        this.platformType = platformType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     *  主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = " 主键")
    private Integer strId;

    /**
     * 借卖id
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "借卖id")
    private Integer dsrId;

    /**
     * 平台类别,1:Amazon;2:ebay
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "平台类别,1:Amazon;2:ebay")
    private String platformType;

    /**
     * 网店名 - 从api中取得
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "网店名 - 从api中取得")
    private String storeName;
}
