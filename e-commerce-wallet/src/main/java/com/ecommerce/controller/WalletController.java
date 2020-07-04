package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import com.ecommerce.service.WalletService;
import com.ecommerce.vojo.WalletAccountVO;
import com.ecommerce.vojo.WalletBalanceVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 钱包控制类
 * Created by yousabla on 2020/7/3.
 */

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
                return new CommonResult(200,"insert successful",walletService.getWalletInfo(info.getAccountName()));
            } else {
                throw BusinessException.INSERT_FAIL;
            }
        }
    }

    @ApiOperation("获取账户钱包信息")
    @PutMapping("/getInfo")
    public CommonResult<WalletBalanceVO> getInfo(@Validated({SelectGroup.class}) @RequestBody WalletAccountVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException().newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            return new CommonResult<WalletBalanceVO>(200,"get wallet info successful",walletService.getWalletInfo(info.getAccountName()));
        }
    }

}
