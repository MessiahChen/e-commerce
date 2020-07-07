package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.pojo.SalSalesOrderLineItem;
import com.ecommerce.pojo.StoStoreOrder;
import com.ecommerce.pojo.StrStore;
import com.ecommerce.service.BvoOrderService;
import com.ecommerce.vo.SaoSalesOrderVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: e-commerce
 * @Package: com.ecommerce.controller
 * @ClassName: BvoOrderController
 * @Description: BVO订单管理
 * @Author: 邱晓淋
 * @CreateDate: 2020/7/6 17:27
 */

@CrossOrigin
@RestController
@RequestMapping("/bvoOrder")
public class BvoOrderController extends BaseController {
    @Autowired
    private BvoOrderService bvoOrderService;

    @ApiOperation("根据dsrId获取订单信息->dsr：借卖方对象")
    @GetMapping("/getVosByDsrId")
    public CommonResult<List<SaoSalesOrderVO>> getVo(@RequestParam int dsrId){
        List<SaoSalesOrderVO> saoSalesOrderVOS = bvoOrderService.getSaoVosByDsrId(dsrId);
        if(saoSalesOrderVOS == null){
            throw BusinessException.SELECT_FAIL;
        }else {
            return CommonResult.success(saoSalesOrderVOS,"返回数据成功");
        }
    }

    @ApiOperation("根据saoId获取sal对象")
    @GetMapping("/getSalBySaoId")
    public CommonResult<SalSalesOrderLineItem> getSalBySaoId(int saoId){
        SalSalesOrderLineItem sal = bvoOrderService.getSalBySaoId(saoId);
        if(sal == null){
            throw BusinessException.SELECT_FAIL;
        }else {
            return CommonResult.success(sal,"返回数据成功");
        }
    }

    @ApiOperation("支付以后，根据saoId修改对应的订单状态")
    @GetMapping("/update")
    public CommonResult<Integer> update(int saoId){
        int result = bvoOrderService.updateOrderBySaoId(saoId);
        if(result == 0){
            throw BusinessException.UPDATE_FAIL;
        }else {
            return CommonResult.success(result,"返回数据成功");
        }
    }

    @ApiOperation("根据saoId获取运费")
    @GetMapping("/getFeeBySaoId")
    public CommonResult<Double> getFeeBySaoId(int saoId){
        double fee = bvoOrderService.getFreightFeeBySaoId(saoId);
        if(fee == -1){
            throw BusinessException.SELECT_FAIL;
        }else {
            return CommonResult.success(fee,"返回数据成功");
        }
    }
}
