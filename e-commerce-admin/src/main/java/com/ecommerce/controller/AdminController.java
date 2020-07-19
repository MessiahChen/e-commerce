package com.ecommerce.controller;

import com.ecommerce.common.base.CommonPage;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.pojo.SysMenu;
import com.ecommerce.pojo.SysResource;
import com.ecommerce.pojo.SysRole;
import com.ecommerce.pojo.SysUser;
import com.ecommerce.security.component.DynamicSecurityMetadataSource;
import com.ecommerce.security.component.DynamicSecurityService;
import com.ecommerce.service.UserService;
import com.ecommerce.vojo.MenuVO;
import com.ecommerce.vojo.PageVO;
import com.ecommerce.vojo.SearchUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yousabla
 */

@CrossOrigin
@Controller
@Api(value = "管理员功能模块", tags = "管理员控制类")
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private UserService userService;
    @Resource
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("获取所有用户")
    @PostMapping("/getAllUser")
    public CommonResult<CommonPage<SysUser>> getAllUser(@RequestBody PageVO pageVO){
        CommonPage<SysUser> commonPage = userService.getAllUser(pageVO);
        if (!commonPage.getList().isEmpty()) {
            return CommonResult.success(commonPage, "获取所有用户成功！");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据用户名模糊匹配用户")
    @PostMapping(value = "/searchUser")
    public CommonResult<CommonPage<SysUser>> searchUser(@RequestBody SearchUserVO searchUserVO) {
        CommonPage<SysUser> commonPage = userService.searchUser(searchUserVO);
        if (!commonPage.getList().isEmpty()) {
            return CommonResult.success(commonPage, "模糊匹配用户列表成功！");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("获取指定用户信息")
    @GetMapping(value = "/infoById")
    @ResponseBody
    public CommonResult<SysUser> getItem(@RequestParam("id") Long id) {
        return CommonResult.success(userService.getItem(id),"获取成功！");
    }

    @ApiOperation("给用户分配角色")
    @PostMapping(value = "/role/update")
    @ResponseBody
    public CommonResult<Integer> updateRole(@RequestParam("adminId") Long adminId,
                                            @RequestParam("roleIds") List<Long> roleIds) {
        dynamicSecurityMetadataSource.clearDataSource();
        int count = userService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count, "分配角色成功！");
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取角色列表")
    @GetMapping(value = "/roles")
    @ResponseBody
    public CommonResult<List<SysRole>> getRoleList() {
        List<SysRole> roleList = userService.getRoleList();
        return CommonResult.success(roleList, "获取角色列表成功！");
    }

    @ApiOperation("获取接口列表")
    @GetMapping(value = "/resources")
    @ResponseBody
    public CommonResult<List<SysResource>> getResourceList() {
        List<SysResource> resourceList = userService.getResourceList();
        return CommonResult.success(resourceList, "获取接口列表成功！");
    }

    @ApiOperation("获取菜单列表")
    @GetMapping(value = "/menus")
    @ResponseBody
    public CommonResult<List<MenuVO>> getMenuList() {
        List<MenuVO> menuList = userService.getMenuList();
        return CommonResult.success(menuList, "获取菜单列表成功！");
    }

    @ApiOperation("更新角色接口权限")
    @PatchMapping(value = "/updateResource")
    @ResponseBody
    public CommonResult<Integer> updateResource(@RequestParam("roleId") Long roleId,
                                                @RequestParam("permissionIds") List<Long> permissionIds) {
        //更改映射规则时需要清除缓存
        dynamicSecurityMetadataSource.clearDataSource();
        int count = userService.updateResource(roleId, permissionIds);
        if (count > 0) {
            return CommonResult.success(count, "更新角色接口权限成功！");
        }
        return CommonResult.failed();
    }

    @ApiOperation("更新角色菜单权限")
    @PatchMapping(value = "/updateMenu")
    @ResponseBody
    public CommonResult<Integer> updateMenu(@RequestParam("roleId") Long roleId,
                                            @RequestParam("permissionIds") List<Long> permissionIds) {
        int count = userService.updateMenu(roleId, permissionIds);
        if (count > 0) {
            return CommonResult.success(count, "更新角色菜单权限成功！");
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取角色接口权限")
    @GetMapping(value = "/getResource")
    @ResponseBody
    public CommonResult<List<Long>> getPermissionResourceList(@RequestParam("roleId") Long roleId) {
        return CommonResult.success(userService.getPermissionResourceList(roleId), "获取角色接口权限成功！");
    }

    @ApiOperation("获取角色菜单权限")
    @GetMapping(value = "/getMenu")
    @ResponseBody
    public CommonResult<List<String>> getPermissionMenuList(@RequestParam("roleId") Long roleId) {
        return CommonResult.success(userService.getPermissionMenuList(roleId), "获取角色菜单权限成功！");
    }
}
