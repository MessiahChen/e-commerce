package com.ecommerce.controller;


import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.pojo.*;
import com.ecommerce.service.OrderService;
import com.ecommerce.vo.IntegerVO;
import com.ecommerce.vo.SaoSalesOrderVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("根据manId获得saoVo对象列表，sao->销售订单")
    @PostMapping("/sao")
    public CommonResult<List<SaoSalesOrderVO>> getSaoByManId(@RequestBody IntegerVO integerVO){
        if(orderService.getSaoByManId(integerVO.getI()) != null){
            return CommonResult.success(orderService.getSaoByManId(integerVO.getI()),"获取sao数据成功");
        }else {
            throw BusinessException.SELECT_FAIL;
        }
    }

    @ApiOperation("根据saoId查询sal，然后根据该sal查询proId，然后根据proId获得pro对象")
    @PostMapping("/getProBySao")
    public CommonResult<ProProduct> getProBySalId(@RequestBody IntegerVO integerVO){
        SalSalesOrderLineItem salSalesOrderLineItem = orderService.getSalBySaoId(integerVO.getI());
        if(salSalesOrderLineItem != null){
            ProProduct proProduct = orderService.getProBySalId(salSalesOrderLineItem.getSalId());
            if(proProduct != null){
                return CommonResult.success(proProduct,"获取pro数据成功");
            }else {
                throw BusinessException.SELECT_FAIL;
            }
        }else {
            throw BusinessException.SELECT_FAIL;
        }
    }

    @ApiOperation("发货接口,根据saoId更新订单状态")
    @PostMapping("/shipment")
    public CommonResult<Boolean> getShipped(@RequestBody int[] saoIds){
        if(orderService.updateOrder(saoIds)){
            return CommonResult.success(orderService.updateOrder(saoIds),"发货成功");
        }else {
            throw BusinessException.UPDATE_FAIL;
        }
    }

    @ApiOperation("退货")
    @PostMapping("/cancel")
    public CommonResult<Boolean> cancelOrder(@RequestBody int[] saoIds){
        if(orderService.cancelOrder(saoIds)){
            return CommonResult.success(orderService.cancelOrder(saoIds),"发货成功");
        }else {
            throw BusinessException.UPDATE_FAIL;
        }
    }
}
