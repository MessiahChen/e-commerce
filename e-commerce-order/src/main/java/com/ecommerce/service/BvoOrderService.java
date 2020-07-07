package com.ecommerce.service;

import com.ecommerce.pojo.*;
import com.ecommerce.vo.SaoSalesOrderVO;

import java.util.List;

/**
 * @ProjectName: e-commerce
 * @Package: com.ecommerce.service
 * @ClassName: BvoOrderService
 * @Description: BVO订单管理接口
 * @Author: 邱晓淋
 * @CreateDate: 2020/7/7 9:10
 */
public interface BvoOrderService {
    //通过dsrId获取strStore对象
    StrStore getStrStoreByDsrId(int dsrId);

    //通过strId获取sto对象列表
    List<StoStoreOrder> getStosByStrId(int strId);

    //通过stoId获取sao对象
    SaoSalesOrder getSaoByStoId(int stoId);

    //根据saoId 查询 sal
    SalSalesOrderLineItem getSalBySaoId(Integer saoId);

    //根据sal的proId查询pro
    ProProduct getProBySalId(Integer salId);

    //根据dsrId返回saoVO对象列表
    List<SaoSalesOrderVO> getSaoVosByDsrId(int dsrId);

    //根据saoId获取运费
    double getFreightFeeBySaoId(int saoId);

    //根据saoId更新订单状态
    int updateOrderBySaoId(int saoId);
}
