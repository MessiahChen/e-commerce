package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonPage;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.base.ResultCode;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.DeleteGroup;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import com.ecommerce.service.ParameterService;
import com.ecommerce.vojo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author yousabla
 */
@Api(value = "参数维护",tags = "参数控制类")
@CrossOrigin
@RestController
@RequestMapping("/parameter")
public class ParameterController extends BaseController {

    @Resource
    ParameterService parameterService;

    @ApiOperation(value = "新增参数")
    @PutMapping(value = "/addParameter")
    public CommonResult addParameter(@Validated({InsertGroup.class}) @RequestBody AddParameterVO addParameterVO, BindingResult result) {
        if (result.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(result),addParameterVO.toString());
        }
        if (!parameterService.add(addParameterVO)) {
            return CommonResult.failed();
        }else {
            return CommonResult.success("add new par successful");
        }
    }

    @ApiOperation("获取所有参数")
    @PostMapping("/getAllParInfo")
    public CommonResult<CommonPage<ParInfoVO>> getAllFlow(@Validated({SelectGroup.class}) @RequestBody ParPageVO parPageVO, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(bindingResult),parPageVO.toString());
        }
        CommonPage<ParInfoVO> commonPage = parameterService.getAllParInfo(parPageVO);
        if (!commonPage.getList().isEmpty()) {
            return CommonResult.success(commonPage, "get all parameters successful");
        } else {
            return CommonResult.failed(ResultCode.THINGS_NOT_FOUND);
        }
    }

    @ApiOperation(value = "模糊匹配参数")
    @PostMapping(value = "/searchPar")
    public CommonResult<CommonPage<ParInfoVO>> searchPar(@Validated({SelectGroup.class}) @RequestBody SearchParVO searchParVO, BindingResult result) {
        if (result.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(result),searchParVO.toString());
        }
        CommonPage<ParInfoVO> commonPage = parameterService.searchPar(searchParVO);
        if (!commonPage.getList().isEmpty()) {
            return CommonResult.success(commonPage, "get similar parameters successful");
        } else {
            return CommonResult.failed(ResultCode.THINGS_NOT_FOUND);
        }
    }

    @ApiOperation(value = "修改前获取原参数记录")
    @GetMapping(value = "/getParWhenUpdate")
    public CommonResult<ParInfoVO> getParWhenUpdate(@RequestParam("parId") Integer parId) {
        return CommonResult.success(parameterService.getParWhenUpdate(parId),"get par successful");
    }

    @ApiOperation(value = "更新参数")
    @PostMapping(value = "/updatePar")
    public CommonResult updatePar(@Validated({UpdateGroup.class}) @RequestBody UpdateParVO updateParVO, BindingResult result) {
        if (result.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(result),updateParVO.toString());
        }
        if (!parameterService.updatePar(updateParVO)) {
            return CommonResult.failed();
        }else {
            return CommonResult.success("update par successful");
        }
    }

    @ApiOperation(value = "删除参数")
    @DeleteMapping(value = "/deletePar")
    public CommonResult deletePar(@RequestParam("parId") Integer parId) {
        if (!parameterService.deletePar(parId)) {
            return CommonResult.failed();
        }else {
            return CommonResult.success("delete par successful");
        }
    }

    @ApiOperation(value = "批量删除参数")
    @DeleteMapping(value = "/batchDeletePar")
    public CommonResult batchDeletePar(@RequestParam("parIds") List<Integer> parIds) {
        if (!parameterService.batchDeletePar(parIds)) {
            return CommonResult.failed();
        }else {
            return CommonResult.success("delete pars successful");
        }
    }

}
