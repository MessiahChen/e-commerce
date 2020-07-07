package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import com.ecommerce.service.WalletFlowService;
import com.ecommerce.vojo.StringVO;
import com.ecommerce.vojo.WalletFlowRecordVO;
import com.ecommerce.vojo.WalletFlowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 钱包流水控制类
 * Created by yousabla on 2020/7/4.
 */

@Api(value = "钱包流水控制，包括充值，提现，查看当前账户流水等功能", tags = "钱包流水控制器")
@CrossOrigin
@RestController
@RequestMapping("/walletFlow")
public class WalletFlowController extends BaseController {

    @Resource
    WalletFlowService walletFlowService;

    @ApiOperation("充值")
    @PatchMapping("/deposit")
    public CommonResult deposit(@Validated({UpdateGroup.class}) @RequestBody WalletFlowVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.INSERT_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletFlowService.deposit(info)) {
                return new CommonResult(20000, "deposit successful");
            } else {
                throw BusinessException.INSERT_FAIL;
            }
        }
    }

    @ApiOperation("提现")
    @PatchMapping("/withdraw")
    public CommonResult withdraw(@Validated({UpdateGroup.class}) @RequestBody WalletFlowVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.INSERT_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletFlowService.withdraw(info)) {
                return new CommonResult(20000, "withdraw successful");
            } else {
                throw BusinessException.INSERT_FAIL;
            }
        }
    }

    @ApiOperation("查看流水")
    @PostMapping("/check")
    public CommonResult<List<WalletFlowRecordVO>> check(@Validated({SelectGroup.class}) @RequestBody StringVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.SELECT_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            return new CommonResult<>(20000, "check successful", walletFlowService.check(info.getAccountName()));
        }
    }

    @ApiOperation("支付")
    @PatchMapping("/pay")
    public CommonResult pay(@Validated({UpdateGroup.class}) @RequestBody WalletFlowVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.INSERT_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletFlowService.pay(info)) {
                return new CommonResult(20000,"pay successful");
            } else {
                throw BusinessException.INSERT_FAIL;
            }
        }
    }

    @ApiOperation("申请退款")
    @PatchMapping("/refund")
    public CommonResult refund(@Validated({UpdateGroup.class}) @RequestBody WalletFlowVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.INSERT_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletFlowService.refund(info)) {
                return new CommonResult(20000,"apply for refund successful");
            } else {
                throw BusinessException.INSERT_FAIL;
            }
        }
    }
}
