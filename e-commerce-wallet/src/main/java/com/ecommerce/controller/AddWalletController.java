package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import com.ecommerce.service.AddWalletService;
import com.ecommerce.vojo.WalletAccountVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 钱包注册控制类
 * Created by yousabla on 2020/7/3.
 */

@CrossOrigin
@RestController
@RequestMapping("/add")
public class AddWalletController extends BaseController {
    @Resource
    private AddWalletService addWalletService;

    @ApiOperation("注册新的钱包账户")
    @PutMapping("/wallet")
    public CommonResult register(@Validated({UpdateGroup.class}) @RequestBody WalletAccountVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.INSERT_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (addWalletService.addWallet(info)) {
                return new CommonResult(200,"insert successful");
            } else {
                throw BusinessException.INSERT_FAIL;
            }
        }
    }

}
