package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonPage;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.base.ResultCode;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.common.validationGroup.UpdateGroup;
import com.ecommerce.service.DataDictionaryService;
import com.ecommerce.vojo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yousabla
 */
@Api(value = "数据字典维护",tags = "数据字典控制类")
@CrossOrigin
@RestController
@RequestMapping("/dataDictionary")
public class DataDictionaryController extends BaseController {

    @Resource
    DataDictionaryService dataDictionaryService;

    @ApiOperation(value = "新增数据字典")
    @PutMapping(value = "/addCdmInfo")
    public CommonResult addCdmInfo(@Validated({InsertGroup.class}) @RequestBody AddCdmVO addCdmVO, BindingResult result) {
        if (result.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(result),addCdmVO.toString());
        }
        if (!dataDictionaryService.add(addCdmVO)) {
            return CommonResult.failed();
        }else {
            return CommonResult.success("add new cdm successful");
        }
    }

    @ApiOperation("获取所有数据字典")
    @PostMapping("/getAllCdmInfo")
    public CommonResult<CommonPage<CdmInfoVO>> getAllCdmInfo(@Validated({SelectGroup.class}) @RequestBody PageVO pageVO, BindingResult bindingResult) throws BusinessException {
        if (bindingResult.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(bindingResult), pageVO.toString());
        }
        CommonPage<CdmInfoVO> commonPage = dataDictionaryService.getAllCdmInfo(pageVO);
        if (!commonPage.getList().isEmpty()) {
            return CommonResult.success(commonPage, "get all cdms successful");
        } else {
            return CommonResult.failed(ResultCode.THINGS_NOT_FOUND);
        }
    }

    @ApiOperation(value = "模糊匹配数据字典")
    @PostMapping(value = "/searchCdm")
    public CommonResult<CommonPage<CdmInfoVO>> searchCdm(@Validated({SelectGroup.class}) @RequestBody SearchCdmVO searchCdmVO, BindingResult result) {
        if (result.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(result),searchCdmVO.toString());
        }
        CommonPage<CdmInfoVO> commonPage = dataDictionaryService.searchCdm(searchCdmVO);
        if (!commonPage.getList().isEmpty()) {
            return CommonResult.success(commonPage, "get similar cdms successful");
        } else {
            return CommonResult.failed(ResultCode.THINGS_NOT_FOUND);
        }
    }

    @ApiOperation(value = "修改前获取原数据字典记录")
    @GetMapping(value = "/getCdmWhenUpdate")
    public CommonResult<CdmInfoVO> getCdmWhenUpdate(@RequestParam("parId") Integer parId) {
        return CommonResult.success(dataDictionaryService.getCdmWhenUpdate(parId),"get cdm successful");
    }

    @ApiOperation(value = "更新数据字典")
    @PostMapping(value = "/updateCdm")
    public CommonResult updateCdm(@Validated({UpdateGroup.class}) @RequestBody UpdateCdmVO updateCdmVO, BindingResult result) {
        if (result.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(result),updateCdmVO.toString());
        }
        if (!dataDictionaryService.updateCdm(updateCdmVO)) {
            return CommonResult.failed();
        }else {
            return CommonResult.success("update cdm successful");
        }
    }

    @ApiOperation(value = "删除数据字典")
    @DeleteMapping(value = "/deleteCdm")
    public CommonResult deleteCdm(@RequestParam("parId") Integer parId) {
        if (!dataDictionaryService.deleteCdm(parId)) {
            return CommonResult.failed();
        }else {
            return CommonResult.success("delete cdm successful");
        }
    }

    @ApiOperation(value = "批量删除数据字典")
    @DeleteMapping(value = "/batchDeleteCdm")
    public CommonResult batchDeleteCdm(@RequestParam("parIds") List<Integer> parIds) {
        if (!dataDictionaryService.batchDeleteCdm(parIds)) {
            return CommonResult.failed();
        }else {
            return CommonResult.success("delete cdms successful");
        }
    }
}
