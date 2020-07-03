package com.ecommerce.service;

import com.ecommerce.pojo.SalSalesOrderLineItem;
import com.ecommerce.pojo.SolStoreOrderLineItem;
import com.ecommerce.pojo.StoStoreOrder;

import java.util.List;

/**
 *  用于品牌商和借卖方的订单管理
 */

public interface OrderService {

    //返回所有的原式订单
    List<StoStoreOrder> getAllStoStoreOrder();

    //根据原始订单返回对应状态的原始订单明细
    List<SolStoreOrderLineItem> getSolStoreOrderLineItem(String stsCd);

    //根据借卖方订单管理对应状态返回销售订单明细
    List<SalSalesOrderLineItem> getSalSalesOrderLineItem(String stsCd);
}
