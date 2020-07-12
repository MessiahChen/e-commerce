package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.dao.CdmCodeMasterMapper;
import com.ecommerce.dao.ParParameterMapper;
import com.ecommerce.service.ParameterService;
import com.ecommerce.vojo.LoginBackVO;
import com.ecommerce.vojo.RegisterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yousabla
 */
@Api(value = "参数维护",tags = "参数控制类")
@CrossOrigin
@RestController
@RequestMapping("/parameter")
public class ParameterController extends BaseController {

    @Resource
    ParParameterMapper parParameterMapper;
    @Resource
    ParameterService parameterService;


//    @ApiOperation(value = "新增参数")
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult<LoginBackVO> register(@Validated({InsertGroup.class}) @RequestBody RegisterVO registerVO, BindingResult result) {
//        if (result.hasErrors()){
//            throw new BusinessException().newInstance(this.getErrorResponse(result),registerVO.toString());
//        }
//        if (!userService.register(registerVO)) {
//            CommonResult.failed();
//        }
//        return CommonResult.success(userService.login(registerVO.getUsername(),registerVO.getPassword()),"注册成功");
//    }

}
