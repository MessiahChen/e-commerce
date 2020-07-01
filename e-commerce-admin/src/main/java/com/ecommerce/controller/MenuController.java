package com.ecommerce.controller;

import com.ecommerce.pojo.SysMenu;
import com.ecommerce.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("添加一个新的菜单")
    @PutMapping("/addMenu")
    public String addUser(@RequestBody SysMenu sysMenu) {
        return menuService.addNewMenu(sysMenu);
    }
}
