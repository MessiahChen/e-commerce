package com.ecommerce.vo;

import com.ecommerce.common.validationGroup.SelectGroup;

import javax.validation.constraints.NotNull;

/**
 * @ProjectName: e-commerce
 * @Package: com.ecommerce.vo
 * @ClassName: StoStorOrderVO
 * @Description: java类作用描述
 * @Author: 邱晓淋
 * @CreateDate: 2020/7/3 15:30
 */
public class StoStoreOrderVO {
    @NotNull(message = "The sts_cd can't be null", groups = {SelectGroup.class})
    String scsId;

    public String getScsId() {
        return scsId;
    }

    public void setScsId(String scsId) {
        this.scsId = scsId;
    }
}
