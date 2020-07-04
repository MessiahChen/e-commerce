package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import com.ecommerce.service.WalletFlowService;
import com.ecommerce.vojo.WalletFlowRecordVO;
import com.ecommerce.vojo.WalletFlowVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.form.SelectTag;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/walletFlow")
public class WalletFlowController extends BaseController {

    @Resource
    WalletFlowService walletFlowService;

    @ApiOperation("充值")
    @PutMapping("/deposit")
    public CommonResult deposit(@Validated({UpdateGroup.class}) @RequestBody WalletFlowVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.UPDATE_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletFlowService.deposit(info)) {
                return new CommonResult(200,"deposit successful");
            } else {
                throw BusinessException.SELECT_FAIL;
            }
        }
    }

    @ApiOperation("提现")
    @PutMapping("/withdraw")
    public CommonResult withdraw (@Validated({InsertGroup.class}) @RequestBody WalletFlowVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.UPDATE_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletFlowService.withdraw(info)) {
                return new CommonResult(200,"withdraw successful");
            } else {
                throw BusinessException.UPDATE_FAIL;
            }
        }
    }

    @ApiOperation("查看流水")
    @PutMapping("/check")
    public CommonResult<WalletFlowRecordVO> check (@Validated({SelectGroup.class}) @RequestBody WalletFlowVO info, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()) {
            throw BusinessException.SELECT_FAIL.newInstance(this.getErrorResponse(bindingResult), info.toString());
        } else {
            if (walletFlowService.withdraw(info)) {
                return new CommonResult(200,"withdraw successful");
            } else {
                throw BusinessException.SELECT_FAIL;
            }
        }
    }
}
