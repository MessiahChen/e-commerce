package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.common.validationGroup.SelectGroup;
import com.ecommerce.service.UserService;
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


/**
 * @author yousabla
 */
@Api(value = "注册，登录，更改密码",tags = "管理员控制类")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;
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
        if (!userService.register(registerVO)) {
            CommonResult.failed();
        }
        return CommonResult.success(userService.login(registerVO.getUsername(),registerVO.getPassword()),"注册成功");
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<LoginBackVO> login(@Validated({SelectGroup.class}) @RequestBody LoginVO loginVO, BindingResult result) {
        LoginBackVO loginBackVO = userService.login(loginVO.getUsername(), loginVO.getPassword());
        loginBackVO.setTokenHead(tokenHead);
        return CommonResult.success(loginBackVO,"");
    }

//    @ApiOperation("获取用户所有权限（包括+-权限）")
//    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
//        List<UmsPermission> permissionList = userService.getPermissionList(adminId);
//        return CommonResult.success(permissionList);
//    }
}
