package com.ecommerce.controller;

import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.base.ResultCode;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(value = "注册，登录，更改密码",tags = "管理员控制类")
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @ApiOperation("注册新用户")
    @PutMapping("/register")
    public CommonResult<List<WalletBalanceVO>> register(@Validated({InsertGroup.class}) @RequestBody WalletAccountVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.INSERT_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletService.addWallet(info)) {
                return new CommonResult<>(20000,"register new wallet account successful",walletService.getWalletInfo(info.getAccountName()));
            } else {
                throw BusinessException.INSERT_FAIL;
            }
        }
    }

    @ApiOperation("登录")
    @PostMapping("/logIn")
    public CommonResult<List<WalletBalanceVO>> getInfo(@RequestBody StringVO info){
        List<WalletBalanceVO> balanceVOs = walletService.getWalletInfo(info.getAccountName());
        if (balanceVOs != null) {
            return CommonResult.success(balanceVOs,"get wallet info successful");
        } else {
            return CommonResult.failed(ResultCode.THINGS_NOT_FOUND);
        }
    }

    @ApiOperation("更改登录密码")
    @PatchMapping("/changePassword")
    public CommonResult changePassword(@Validated({UpdateGroup.class}) @RequestBody WalletPasswordVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.UPDATE_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletService.changePassword(info)) {
                return new CommonResult(20000,"change password successful");
            } else {
                throw BusinessException.UPDATE_FAIL;
            }
        }
    }
}
