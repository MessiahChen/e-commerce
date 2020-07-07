package com.ecommerce.pojo;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class FreightCost implements Serializable {
    /**
     * 国家及省份编码的组合，作为主键
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "国家及省份编码的组合，作为主键")
    private String countryAndProvinceCode;

    /**
     * 运费
     *
     * @mbg.generated
     */
    @ApiModelProperty(value = "运费")
    private Double shippingFee;

    private static final long serialVersionUID = 1L;

    public String getCountryAndProvinceCode() {
        return countryAndProvinceCode;
    }

    public void setCountryAndProvinceCode(String countryAndProvinceCode) {
        this.countryAndProvinceCode = countryAndProvinceCode;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", countryAndProvinceCode=").append(countryAndProvinceCode);
        sb.append(", shippingFee=").append(shippingFee);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}