package com.ecommerce.service;

import com.ecommerce.pojo.SysResource;
import com.ecommerce.pojo.SysRole;
import com.ecommerce.pojo.SysUser;
import com.ecommerce.vojo.RegisterVO;
import com.ecommerce.vojo.UpdatePasswordVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    /**
     * 根据用户名获取用户信息
     */
    SysUser getUserByName(String username);

    /**
     * 注册功能
     */
    SysUser register(RegisterVO registerVO);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    SysUser getItem(Long id);

    /**
     * 更新用户的身份
     */
    public int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取所有角色
     */
    List<SysRole> getRoleList();

    /**
     * 获取可访问资源列表
     */
    List<SysResource> getResourceList();

    /**
     * 修改用户权限
     */
    @Transactional
    int updatePermission(Long adminId, List<Long> permissionIds);

    /**
     * 获取角色所有权限
     */
    List<Long> getPermissionList(Long roleId);

    /**
     * 修改密码
     */
    int updatePassword(UpdatePasswordVO updatePasswordVO);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);
}
