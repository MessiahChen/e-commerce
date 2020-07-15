package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.pojo.SysResource;
import com.ecommerce.pojo.SysRole;
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
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@Api(tags = "UserController", description = "后台用户管理")
@RequestMapping("/user")
public class UserController extends BaseController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private UserService adminService;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    @ResponseBody
    public CommonResult<SysUser> register(@RequestBody RegisterVO registerVO, BindingResult result) {
        if (result.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(result),registerVO.toString());
        }
        SysUser umsAdmin = adminService.register(registerVO);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin,"注册成功！");
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    @ResponseBody
    public CommonResult login(@RequestBody LoginVO loginVO, BindingResult result) {
        if (result.hasErrors()){
            throw new BusinessException().newInstance(this.getErrorResponse(result),loginVO.toString());
        }
        String token = adminService.login(loginVO.getUsername(),loginVO.getPassword());
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
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap,"token刷新成功！");
    }

    @ApiOperation("修改用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestBody UpdatePasswordVO updatePasswordVO) {
        int status = adminService.updatePassword(updatePasswordVO);
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

//    @ApiOperation(value = "获取当前登录用户信息")
//    @GetMapping(value = "/info/{username}")
//    @ResponseBody
//    public CommonResult getAdminInfo(@PathVariable String username) {
//        SysUser sysUser = adminService.getUserByName(username);
//        Map<String, Object> data = new HashMap<>();
//        data.put("username", sysUser.getUsername());
//        data.put("roles", new String[]{"TEST"});
////        data.put("menus", roleService.getMenuList(sysUser.getId()));
//        data.put("icon", sysUser.getIcon());
//        return CommonResult.success(data,"获取当前用户信息成功！");
//    }
//
//
//    @ApiOperation("获取指定用户信息")
//    @GetMapping(value = "/{id}")
//    @ResponseBody
//    public CommonResult<SysUser> getItem(@PathVariable Long id) {
//        SysUser user = adminService.getItem(id);
//        return CommonResult.success(user,"获取成功！");
//    }

    @ApiOperation("给用户分配角色")
    @PostMapping(value = "/role/update")
    @ResponseBody
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count,"分配角色成功");
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取角色列表")
    @GetMapping(value = "/roles")
    @ResponseBody
    public CommonResult<List<SysRole>> getRoleList() {
        List<SysRole> roleList = adminService.getRoleList();
        return CommonResult.success(roleList,"获取角色列表成功！");
    }

    @ApiOperation("获取资源列表")
    @GetMapping(value = "/resources")
    @ResponseBody
    public CommonResult<List<SysResource>> getResourceList() {
        List<SysResource> resourceList = adminService.getResourceList();
        return CommonResult.success(resourceList,"获取资源列表成功！");
    }

    @ApiOperation("更新角色权限")
    @PostMapping(value = "/permission/update")
    @ResponseBody
    public CommonResult updatePermission(@RequestParam Long roleId,
                                         @RequestParam("permissionIds") List<Long> permissionIds) {
        int count = adminService.updatePermission(roleId, permissionIds);
        if (count > 0) {
            return CommonResult.success(count,"更新权限成功");
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取角色所有权限")
    @GetMapping(value = "/permission/{roleId}")
    @ResponseBody
    public CommonResult<List<Long>> getPermissionList(@PathVariable Long roleId) {
        return CommonResult.success(adminService.getPermissionList(roleId),"获取角色权限成功！");
    }
}
