package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.pojo.SalSalesOrderLineItem;
import com.ecommerce.pojo.StoStoreOrder;
import com.ecommerce.pojo.StrStore;
import com.ecommerce.service.BvoOrderService;
import com.ecommerce.vo.IntegerVO;
import com.ecommerce.vo.SaoSalesOrderVO;
import com.ecommerce.vo.StringVO;
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
    @PostMapping("/getVosByDsrId")
    public CommonResult<List<SaoSalesOrderVO>> getVo(@RequestBody IntegerVO integerVO){
        List<SaoSalesOrderVO> saoSalesOrderVOS = bvoOrderService.getSaoVosByDsrId(integerVO.getI());
        if(saoSalesOrderVOS == null){
            throw BusinessException.SELECT_FAIL;
        }else {
            return CommonResult.success(saoSalesOrderVOS,"返回数据成功");
        }
    }

    @ApiOperation("根据saoId获取sal对象")
    @PostMapping("/getSalBySaoId")
    public CommonResult<SalSalesOrderLineItem> getSalBySaoId(@RequestBody IntegerVO integerVO){
        SalSalesOrderLineItem sal = bvoOrderService.getSalBySaoId(integerVO.getI());
        if(sal == null){
            throw BusinessException.SELECT_FAIL;
        }else {
            return CommonResult.success(sal,"返回数据成功");
        }
    }

    @ApiOperation("支付以后，根据saoId修改对应的订单状态")
    @PostMapping("/update")
    public CommonResult<Integer> update(@RequestBody int[] saoIds){
        int result = bvoOrderService.updateOrderBySaoId(saoIds);
        if(result == 0){
            throw BusinessException.UPDATE_FAIL;
        }else {
            return CommonResult.success(result,"返回数据成功");
        }
    }

    @ApiOperation("根据saoId获取运费")
    @PostMapping("/getFeeBySaoId")
    public CommonResult<Double> getFeeBySaoId(@RequestBody StringVO stringVO){
        double fee = bvoOrderService.getFreightFee  (stringVO.getString());
        if(fee == -1){
            throw BusinessException.SELECT_FAIL;
        }else {
            return CommonResult.success(fee,"返回数据成功");
        }
    }
}
