package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.base.ResultCode;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import com.ecommerce.service.WalletService;
import com.ecommerce.vojo.WalletAccountVO;
import com.ecommerce.vojo.WalletBalanceVO;
import com.ecommerce.vojo.WalletFlowVO;
import com.ecommerce.vojo.WalletPasswordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 钱包控制类
 * Created by yousabla on 2020/7/3.
 */

@Api(value = "钱包控制器,负责控制钱包账户的注册，登录（获取钱包信息），更改密码", tags = "钱包控制器")
@CrossOrigin
@RestController
@RequestMapping("/wallet")
public class WalletController extends BaseController {
    @Resource
    private WalletService walletService;

    @ApiOperation("注册新的钱包账户")
    @PutMapping("/register")
    public CommonResult<WalletBalanceVO> register(@Validated({InsertGroup.class}) @RequestBody WalletAccountVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.INSERT_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletService.addWallet(info)) {
                return new CommonResult<>(200,"register new wallet ccount successful",walletService.getWalletInfo(info.getAccountName()));
            } else {
                throw BusinessException.INSERT_FAIL;
            }
        }
    }

    @ApiOperation("获取账户钱包信息")
    @PostMapping("/getInfo")
    public CommonResult<WalletBalanceVO> getInfo(@RequestBody String accountName){
        WalletBalanceVO balanceVO = walletService.getWalletInfo(accountName);
        if (balanceVO != null) {
            return CommonResult.success(balanceVO,"get wallet info successful");
        } else {
            return CommonResult.failed(ResultCode.THINGS_NOT_FOUND);
        }
    }

    @ApiOperation("更改支付密码")
    @PatchMapping("/changePassword")
    public CommonResult changePassword(@Validated({UpdateGroup.class}) @RequestBody WalletPasswordVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.UPDATE_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletService.changePassword(info)) {
                return new CommonResult(200,"change password successful");
            } else {
                throw BusinessException.INSERT_FAIL;
            }
        }
    }

    @ApiOperation("支付")
    @PatchMapping("/pay")
    public CommonResult pay(@Validated({UpdateGroup.class}) @RequestBody WalletFlowVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.UPDATE_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletService.pay(info)) {
                return new CommonResult(200,"pay successful");
            } else {
                throw BusinessException.UPDATE_FAIL;
            }
        }
    }

    @ApiOperation("申请退款")
    @PatchMapping("/refund")
    public CommonResult refund(@Validated({UpdateGroup.class}) @RequestBody WalletFlowVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.UPDATE_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletService.refund(info)) {
                return new CommonResult(200,"apply for refund successful");
            } else {
                throw BusinessException.UPDATE_FAIL;
            }
        }
    }

}
