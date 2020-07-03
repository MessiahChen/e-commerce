package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.service.AddWalletService;
import com.ecommerce.service.GetWalletInfoService;
import com.ecommerce.vojo.WalletAccountVO;
import com.ecommerce.vojo.WalletBalanceVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/get")
public class GetWalletInfoController extends BaseController {

    @Resource
    private GetWalletInfoService getWalletInfoService;

    @ApiOperation("获取账户钱包信息")
    @PutMapping("/walletInfo")
    public CommonResult<WalletBalanceVO> getInfo(@Validated({SelectGroup.class}) @RequestBody WalletAccountVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw new BusinessException().newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            return new CommonResult<WalletBalanceVO>(200,"get wallet info successful",getWalletInfoService.getWalletInfo(info.getAccountName()));
        }
    }


}
