package com.ecommerce.service.impl;

import com.ecommerce.dao.SysMenuMapper;
import com.ecommerce.dto.SysMenuDTO;
import com.ecommerce.pojo.SysMenu;
import com.ecommerce.pojo.SysMenuExample;
import com.ecommerce.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public boolean addNewMenu(SysMenu sysMenu) {
        sysMenuMapper.insert(sysMenu);
        return true;
    }

}
