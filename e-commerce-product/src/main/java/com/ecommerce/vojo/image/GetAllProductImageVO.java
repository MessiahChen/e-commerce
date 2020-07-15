package com.ecommerce.vojo.image;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetAllProductImageVO {
    private Integer manId;

    public Integer getManId() {
        return manId;
    }

    public void setManId(Integer manId) {
        this.manId = manId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private Integer pageNum;
    private Integer pageSize;
}
