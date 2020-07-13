package com.ecommerce.service;

import com.ecommerce.pojo.SysUser;
import com.ecommerce.vojo.LoginBackVO;
import com.ecommerce.vojo.RegisterVO;

import java.util.List;

public interface UserService {

    /**
     * 根据用户名获取后台管理员
     */
    SysUser getAdminByUsername(String username);

    /**
     * 注册功能
     */
    Boolean register(RegisterVO registerVO);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    LoginBackVO login(String username, String password);

//    UserDetails loadUserByUsername(String username);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
//    List<UmsPermission> getPermissionList(Long adminId);
}
