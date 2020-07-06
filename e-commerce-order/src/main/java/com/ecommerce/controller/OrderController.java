package com.ecommerce.controller;


import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.pojo.*;
import com.ecommerce.service.OrderService;
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

    /**
     * sto->原式订单信息
     * sol->原式订单详细信息
     * sao->销售订单
     * sal->销售订单明细
     */
    @ApiOperation("返回所有的sto对象,sto->原式订单信息")
    @GetMapping("/sto")
    public CommonResult<List<StoStoreOrder>> getStoStoreOrder(){
        if(orderService.getAllStoStoreOrder() != null)
            return CommonResult.success(orderService.getAllStoStoreOrder(),"获取sto数据成功");
        else
            throw BusinessException.SELECT_FAIL;
    }

    @ApiOperation("根据状态返回sol对象,sol->原式订单详细信息")
    @GetMapping("/sol")
    public CommonResult<List<SolStoreOrderLineItem>>  getSolStoreOrderLineItem(@RequestParam String stsCd){
        if(orderService.getSolStoreOrderLineItem(stsCd) != null){
            return CommonResult.success("获取sol数据成功");
        }else {
            throw BusinessException.SELECT_FAIL;
        }
    }

    @ApiOperation("根据状态sal对象,sal->销售订单详细信息")
    @GetMapping("/sal")
    public CommonResult<List<SalSalesOrderLineItem>>  getSalSalesOrderLineItem(@RequestParam String stsCd){
        if(orderService.getSalSalesOrderLineItem(stsCd) != null){
            return CommonResult.success("获取sal数据成功");
        }else {
            throw BusinessException.SELECT_FAIL;
        }
    }

    @ApiOperation("根据manId获得saoVo对象列表，sao->销售订单")
    @GetMapping("/sao")
    public CommonResult<List<SaoSalesOrderVO>> getSaoByManId(@RequestParam int manId){
        if(orderService.getSaoByManId(manId) != null){
            return CommonResult.success("获取sao数据成功");
        }else {
            throw BusinessException.SELECT_FAIL;
        }
    }

    @ApiOperation("根据saoId查询sal，然后根据该sal查询proId，然后根据proId获得pro对象")
    @GetMapping("getProBySao")
    public CommonResult<ProProduct> getProBySalId(@RequestParam int saoId){
        SalSalesOrderLineItem salSalesOrderLineItem = orderService.getSalBySaoId(saoId);
        if(salSalesOrderLineItem != null){
            ProProduct proProduct = orderService.getProBySalId(salSalesOrderLineItem.getSalId());
            if(proProduct != null){
                return CommonResult.success("获取pro数据成功");
            }else {
                throw BusinessException.SELECT_FAIL;
            }
        }else {
            throw BusinessException.SELECT_FAIL;
        }
    }
}
