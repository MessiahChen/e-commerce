package com.ecommerce.controller;


import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.pojo.SolStoreOrderLineItem;
import com.ecommerce.pojo.StoStoreOrder;
import com.ecommerce.pojo.SysMenu;
import com.ecommerce.service.OrderService;
import com.ecommerce.vo.StoStoreOrderVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    @PostMapping("/sol")
    public CommonResult<SysMenu>  getSolStoreOrderLineItem(@RequestParam String stsCd){
        if(orderService.getSolStoreOrderLineItem(stsCd) != null){
            return CommonResult.success("获取sol数据成功");
        }else {
            throw BusinessException.SELECT_FAIL;
        }
    }

    @ApiOperation("根据状态sal对象,sol->原式订单详细信息")
    @PostMapping("/sal")
    public CommonResult<SysMenu>  getSalSalesOrderLineItem(@RequestParam String stsCd){
        if(orderService.getSalSalesOrderLineItem(stsCd) != null){
            return CommonResult.success("获取sal数据成功");
        }else {
            throw BusinessException.SELECT_FAIL;
        }
    }
}
