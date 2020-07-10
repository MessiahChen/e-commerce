package com.ecommerce.service;

import com.ecommerce.pojo.*;
import com.ecommerce.vo.SaoSalesOrderVO;

import java.util.List;

/**
 *  用于品牌商和借卖方的订单管理
 */

public interface OrderService {

//    //返回所有的原式订单
//    List<StoStoreOrder> getAllStoStoreOrder();
//
//    //根据原始订单返回对应状态的原始订单明细
//    List<SolStoreOrderLineItem> getSolStoreOrderLineItem(String stsCd);
//
//    //根据借卖方订单管理对应状态返回销售订单明细
//    List<SalSalesOrderLineItem> getSalSalesOrderLineItem(String stsCd);

    //根据manId查询sao信息
    List<SaoSalesOrderVO> getSaoByManId(int manId);

    //根据saoId 查询 sal
    SalSalesOrderLineItem getSalBySaoId(Integer saoId);

    //根据sal的proId查询pro
    ProProduct getProBySalId(Integer salId);

    //根据saoId更新订单状态
    boolean updateOrder(int[] saoIds);

    //退货
    boolean cancelOrder(int[] saoIds);
}
