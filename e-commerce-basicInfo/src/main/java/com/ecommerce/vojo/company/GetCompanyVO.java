package com.ecommerce.vojo.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCompanyVO {
    private Integer manId;

    public Integer getManId() {
        return manId;
    }

    public void setManId(Integer manId) {
        this.manId = manId;
    }
}
