package com.ecommerce.service.impl;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.dao.DsrDropshipperMapper;
import com.ecommerce.dao.EbaEbayAuthprizationMapper;
import com.ecommerce.dao.StrStoreMapper;
import com.ecommerce.dao.SysUserMapper;
import com.ecommerce.pojo.*;
import com.ecommerce.service.StoreService;
import com.ecommerce.vojo.store.GetStoreVO;
import com.ecommerce.vojo.store.StoreAddVO;
import com.ecommerce.vojo.store.StoreEntryVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StrStoreMapper strStoreMapper;
    @Autowired
    private DsrDropshipperMapper dsrDropshipperMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private EbaEbayAuthprizationMapper ebaEbayAuthprizationMapper;

    @Override
    public CommonPage<StoreEntryVO> getStore(GetStoreVO getStoreVO){
        Page<StrStore> storePage = PageHelper.startPage(getStoreVO.getPageNum(), getStoreVO.getPageSize()).doSelectPage(() -> {
            StrStoreExample strStoreExample = new StrStoreExample();
            StrStoreExample.Criteria criteria = strStoreExample.createCriteria();
            criteria.andStrIdEqualTo(getStoreVO.getStrId());
            strStoreMapper.selectByExample(strStoreExample);
        });

        List<StoreEntryVO> result = new ArrayList<>();
        for (StrStore store : storePage.getResult()) {
            StoreEntryVO storeEntryVO = new StoreEntryVO();
            storeEntryVO.setDsrId(store.getDsrId());
            storeEntryVO.setDsrId(store.getDsrId());
            storeEntryVO.setPlatformType(store.getPlatformType());
            storeEntryVO.setStoreName(store.getStoreName());
            result.add(storeEntryVO);
        }
        return CommonPage.restPage(result, storePage);
    }

    @Override
    public boolean addStore(StoreAddVO storeAddVO){
        //判断是否为第一次新增网店的用户
        DsrDropshipper tempDsr = dsrDropshipperMapper.selectByPrimaryKey(storeAddVO.getDsrId());
        if(tempDsr == null){
            tempDsr.setDsrId(storeAddVO.getDsrId());
            tempDsr.setName(storeAddVO.getDsrName());
        }


    }
}
