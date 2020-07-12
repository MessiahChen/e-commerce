package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.service.AdminService;
import com.ecommerce.vojo.LoginBackVO;
import com.ecommerce.vojo.LoginVO;
import com.ecommerce.vojo.RegisterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Api(value = "注册，登录，更改密码",tags = "管理员控制类")
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Resource
    private AdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<LoginBackVO> register(@Validated({InsertGroup.class}) @RequestBody RegisterVO registerVO, BindingResult result) {
        if (result.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(result),registerVO.toString());
        }
        if (!adminService.register(registerVO)) {
            CommonResult.failed();
        }
        return CommonResult.success(adminService.login(registerVO.getUsername(),registerVO.getPassword()),"注册成功");
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<LoginBackVO> login(@Validated({SelectGroup.class}) @RequestBody LoginVO loginVO, BindingResult result) {
        LoginBackVO loginBackVO = adminService.login(loginVO.getUsername(), loginVO.getPassword());
        loginBackVO.setTokenHead(tokenHead);
        return CommonResult.success(loginBackVO,"");
    }

//    @ApiOperation("获取用户所有权限（包括+-权限）")
//    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
//        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
//        return CommonResult.success(permissionList);
//    }
}
