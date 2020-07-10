package com.ecommerce.vojo.store;

import java.util.Date;

/**
 * @ProjectName: e-commerce
 * @Package: com.ecommerce.vojo.store
 * @ClassName: DsrVO
 * @Description: 前端往后端穿的借卖方信息
 * @Author: 邱晓淋
 * @CreateDate: 2020/7/10 9:03
 */
public class DsrVO {
    String name;
    String createdBy;
    Date creationDate;
    int dsrId;

    public int getDsrId() {
        return dsrId;
    }

    public void setDsrId(int dsrId) {
        this.dsrId = dsrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
