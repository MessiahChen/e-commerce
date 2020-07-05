package com.ecommerce.service.impl;

import com.ecommerce.dao.SalSalesOrderLineItemMapper;
import com.ecommerce.dao.SaoSalesOrderMapper;
import com.ecommerce.dao.SolStoreOrderLineItemMapper;
import com.ecommerce.dao.StoStoreOrderMapper;
import com.ecommerce.pojo.*;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private StoStoreOrderMapper stoStoreOrderMapper;
    @Autowired
    private SolStoreOrderLineItemMapper solStoreOrderLineItemMapper;
    @Autowired
    private SalSalesOrderLineItemMapper salSalesOrderLineItemMapper;
    @Autowired
    private SaoSalesOrderMapper saoSalesOrderMapper;

    @Override
    public List<StoStoreOrder> getAllStoStoreOrder(){
        StoStoreOrderExample stoStoreOrderExample = new StoStoreOrderExample();
        return stoStoreOrderMapper.selectByExample(stoStoreOrderExample);
    }

    @Override
    public List<SolStoreOrderLineItem> getSolStoreOrderLineItem(String stsCd){
        //获取状态与stsCd一致的sto对象
        StoStoreOrderExample stoStoreOrderExample = new StoStoreOrderExample();
        StoStoreOrderExample.Criteria criteria = stoStoreOrderExample.createCriteria();
        criteria.andStsCdEqualTo(stsCd);
        List<StoStoreOrder> stoStoreOrders = stoStoreOrderMapper.selectByExample(stoStoreOrderExample);

        //没找到符合状态的订单，返回null
        if(stoStoreOrders.size() == 0 || stoStoreOrders == null){
            return null;
        }

        /**
         * 在获取的stoStoreOrders内遍历，然后在数据库中获取对应的sol对象列表
         */
        SolStoreOrderLineItemExample solStoreOrderLineItemExample = new SolStoreOrderLineItemExample();
        SolStoreOrderLineItemExample.Criteria solCriteria = solStoreOrderLineItemExample.createCriteria();

        //用于保存返回值
        List<SolStoreOrderLineItem> results = new ArrayList<>();
        //开始遍历
        for(StoStoreOrder tempSto : stoStoreOrders){
            solCriteria.andStoIdEqualTo(tempSto.getStoId());
            SolStoreOrderLineItem temp = new SolStoreOrderLineItem();
            List<SolStoreOrderLineItem> tempList =  solStoreOrderLineItemMapper.selectByExample(solStoreOrderLineItemExample);
            if(tempList.size() == 0 || tempList == null){
                continue;
            }else {
                temp = tempList.get(0);
                results.add(temp);
            }
        }

        return results;
    }

    @Override
    public List<SalSalesOrderLineItem> getSalSalesOrderLineItem(String stsCd){
        //获取状态与orderSts一致的sao对象
        SaoSalesOrderExample saoSalesOrderExample = new SaoSalesOrderExample();
        SaoSalesOrderExample.Criteria criteria = saoSalesOrderExample.createCriteria();
        criteria.andOrderStsEqualTo(stsCd);
        List<SaoSalesOrder> saoSalesOrders = saoSalesOrderMapper.selectByExample(saoSalesOrderExample);

        //没找到符合状态的订单，返回null
        if(saoSalesOrders.size() == 0 || saoSalesOrders == null){
            return null;
        }

        /**
         * 在获取的stoStoreOrders内遍历，然后在数据库中获取对应的sol对象列表
         */
        SalSalesOrderLineItemExample salSalesOrderLineItemExample = new SalSalesOrderLineItemExample();
        SalSalesOrderLineItemExample.Criteria salCriteria = salSalesOrderLineItemExample.createCriteria();


        //用于保存返回值
        List<SalSalesOrderLineItem> results = new ArrayList<>();
        //开始遍历
        for(SaoSalesOrder tempSao : saoSalesOrders){
            salCriteria.andSaoIdEqualTo(tempSao.getSaoId());
            SalSalesOrderLineItem temp = new SalSalesOrderLineItem();
            List<SalSalesOrderLineItem> tempList =  salSalesOrderLineItemMapper.selectByExample(salSalesOrderLineItemExample);
            if(tempList.size() == 0 || tempList == null){
                continue;
            }else {
                temp = tempList.get(0);
                results.add(temp);
            }
        }

        return results;
    }

    @Override
    //根据manId查询sao信息
    public List<SaoSalesOrder> getSaoByManId(int manId){
        //用SaoSalesOrderExample来获取对应的Sao列表
        SaoSalesOrderExample saoSalesOrderExample = new SaoSalesOrderExample();
        SaoSalesOrderExample.Criteria criteria = saoSalesOrderExample.createCriteria();
        //选中条件-> manId相等
        criteria.andManIdEqualTo(manId);
        return saoSalesOrderMapper.selectByExample(saoSalesOrderExample);
    }
}
