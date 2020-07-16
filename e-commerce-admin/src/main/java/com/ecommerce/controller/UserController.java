package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.pojo.SysUser;
import com.ecommerce.service.UserService;
import com.ecommerce.vojo.LoginVO;
import com.ecommerce.vojo.RegisterVO;
import com.ecommerce.vojo.UpdatePasswordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@Api(value = "用户信息维护",tags = "用户控制类")
@RequestMapping("/user")
public class UserController extends BaseController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private UserService userService;

    @ApiOperation(value = "用户注册")
    @PutMapping(value = "/register")
    @ResponseBody
    public CommonResult<SysUser> register(@RequestBody RegisterVO registerVO, BindingResult result) {
        if (result.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(result),registerVO.toString());
        }
        SysUser umsAdmin = userService.register(registerVO);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin,"注册成功！");
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    @ResponseBody
    public CommonResult<Map<String,String>> login(@RequestBody LoginVO loginVO, BindingResult result) {
        if (result.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(result),loginVO.toString());
        }
        String token = userService.login(loginVO.getUsername(),loginVO.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap,"");
    }

    @ApiOperation(value = "刷新token")
    @GetMapping(value = "/refreshToken")
    @ResponseBody
    public CommonResult<Map<String,String>> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = userService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap,"token刷新成功！");
    }

    @ApiOperation("修改用户密码")
    @PostMapping(value = "/updatePassword")
    @ResponseBody
    public CommonResult updatePassword(@RequestBody UpdatePasswordVO updatePasswordVO) {
        int status = userService.updatePassword(updatePasswordVO);
        if (status > 0) {
            return CommonResult.success(status,"修改用户密码成功！");
        } else if (status == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return CommonResult.failed("找不到该用户");
        } else if (status == -3) {
            return CommonResult.failed("旧密码错误");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/currentInfo")
    @ResponseBody
    public CommonResult<Map<String, Object>> getAdminInfo(@RequestParam("username") String username) {
        SysUser sysUser = userService.getUserByName(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", sysUser.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("menus", userService.getPermissionMenuList(sysUser.getId()));
        data.put("resources", userService.getPermissionResourceList(sysUser.getId()));
        return CommonResult.success(data,"获取当前用户信息成功！");
    }



}
