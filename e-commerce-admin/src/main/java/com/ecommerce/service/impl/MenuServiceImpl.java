package com.ecommerce.service.impl;

import com.ecommerce.dao.SysMenuMapper;
import com.ecommerce.pojo.SysMenu;
import com.ecommerce.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public String addNewMenu(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
        return "成功！";
    }
}
