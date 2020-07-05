package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonPage;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.base.ResultCode;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.service.WalletAdminService;
import com.ecommerce.vojo.WalletAdminVO;
import com.ecommerce.vojo.WalletPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(value = "负责获取待审核的流水账单，审核待审核的账单（待完成）", tags = "管理员-钱包流水控制器")
@CrossOrigin
@RestController
@RequestMapping("/walletAdmin")
public class WalletAdminController extends BaseController {

    @Resource
    private WalletAdminService walletAdminService;

    @ApiOperation("管理员获取所有待审核wallet流水")
    @GetMapping("/getAllFlow")
    public CommonResult<CommonPage<WalletAdminVO>> getAllFlow(@Validated({SelectGroup.class}) @RequestBody WalletPageVO walletPageVO, BindingResult bindingResult) throws BusinessException {
        CommonPage<WalletAdminVO> commonPage = walletAdminService.getAllFlow(walletPageVO);
        if (!commonPage.getList().isEmpty()) {
            return CommonResult.success(commonPage, "insert successful");
        } else {
            return CommonResult.failed(ResultCode.THINGS_NOT_FOUND);
        }
    }
}
