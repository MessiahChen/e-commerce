package com.ecommerce.vo;

/**
 * @ProjectName: e-commerce
 * @Package: com.ecommerce.vo
 * @ClassName: ShippingVO
 * @Description: 前端传给后端的发货的订单和订单号的VO
 * @Author: 邱晓淋
 * @CreateDate: 2020/7/10 18:12
 */
public class ShippingVO {
    int saoId;
    String trackNo;

    public int getSaoId() {
        return saoId;
    }

    public void setSaoId(int saoId) {
        this.saoId = saoId;
    }

    public String getTrackNo() {
        return trackNo;
    }

    public void setTrackNo(String trackNo) {
        this.trackNo = trackNo;
    }
}
