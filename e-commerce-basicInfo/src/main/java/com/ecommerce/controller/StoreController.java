package com.ecommerce.controller;

import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.pojo.StrStore;
import com.ecommerce.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api(value = "BVO-店铺管理控制器", tags = "BVO-店铺管理控制器")
@RestController
@RequestMapping("")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @ApiOperation("通过dsrId获得str对象列表")
    @GetMapping("/getStrsByDsrId")
    public CommonResult<List<StrStore>> getStrsByDsrId(@RequestParam int dsrId){
        List<StrStore> strStores = storeService.getStrsByDsrId(dsrId);
        if(strStores.size() == 0 || strStores == null){
            throw BusinessException.SELECT_FAIL;
        }else {
            return CommonResult.success(strStores,"返回数据成功");
        }
    }
}
