package com.ecommerce.controller;

import com.ecommerce.common.base.BaseController;
import com.ecommerce.common.base.CommonResult;
import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.common.validationGroup.InsertGroup;
import com.ecommerce.pojo.SysMenu;
import com.ecommerce.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("添加一个新的菜单")
    @PutMapping("/addMenu")
    public CommonResult<SysMenu> addUser(@Validated({InsertGroup.class}) @RequestBody SysMenu sysMenu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw BusinessException.INSERT_FAIL.newInstance(this.getErrorResponse(bindingResult), sysMenu.toString());
        } else {
            if (menuService.addNewMenu(sysMenu)) {
                return CommonResult.success("插入成功");
            } else {
                throw BusinessException.INSERT_FAIL;
            }
        }
    }
}
