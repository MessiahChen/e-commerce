package com.ecommerce.vojo.image;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductStatusVO {
    private Integer proId;
    private String status;

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
