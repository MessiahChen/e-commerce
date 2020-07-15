package com.ecommerce.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ecommerce.dao.*;
import com.ecommerce.pojo.*;
import com.ecommerce.security.util.JwtTokenUtil;
import com.ecommerce.service.UserService;
import com.ecommerce.utils.AdminUserDetails;
import com.ecommerce.vojo.RegisterVO;
import com.ecommerce.vojo.UpdatePasswordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRoleRelationMapper sysUserRoleRelationMapper;
    @Resource
    private SysResourceMapper sysResourceMapper;
    @Resource
    private SysUserLoginLogMapper sysUserLoginLogMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRoleResourceRelationMapper sysRoleResourceRelationMapper;

    @Override
    public SysUser getUserByName(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysUser> users = sysUserMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public SysUser register(RegisterVO registerVO) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(registerVO, user);
        user.setCreateTime(new Date());
        user.setStatus(1);
        //查询是否有相同用户名的用户
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(user.getUsername());
        List<SysUser> umsAdminList = sysUserMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        int userId = sysUserMapper.insertSelective(user);
        //插入角色权限
        SysUserRoleRelation sysUserRoleRelation = new SysUserRoleRelation();
        sysUserRoleRelation.setAdminId((long) userId);
        sysUserRoleRelation.setRoleId((long) registerVO.getRoleId());
        sysUserRoleRelationMapper.insertSelective(sysUserRoleRelation);
        //TODO 插入对应角色表 并返回给user表其ID
        return user;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            //通过自定义方法装载UserDetails类
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        SysUser user = getUserByName(username);
        SysUserLoginLog loginLog = new SysUserLoginLog();
        loginLog.setMemberId(user.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;
        loginLog.setIp(request != null ? request.getRemoteAddr() : null);
        sysUserLoginLogMapper.insertSelective(loginLog);
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public SysUser getItem(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        SysUserRoleRelationExample example = new SysUserRoleRelationExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        sysUserRoleRelationMapper.deleteByExample(example);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            roleIds.forEach(roleId -> {
                SysUserRoleRelation roleRelation = new SysUserRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                sysUserRoleRelationMapper.insertSelective(roleRelation);
            });

        }
        return count;
    }

    @Override
    public List<SysRole> getRoleList() {
        SysRoleExample example = new SysRoleExample();
        return sysRoleMapper.selectByExample(example);
    }

    @Override
    public List<SysResource> getResourceList() {
        SysResourceExample example = new SysResourceExample();
        return sysResourceMapper.selectByExample(example);
    }

    @Override
    public int updatePermission(Long roleId, List<Long> resourceIds) {
        //删除原所有权限关系
        SysRoleResourceRelationExample example = new SysRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        sysRoleResourceRelationMapper.deleteByExample(example);

        int count = 0;
        for (Long resourceId:resourceIds) {
            SysRoleResourceRelation sysRoleResourceRelation = new SysRoleResourceRelation();
            sysRoleResourceRelation.setRoleId(roleId);
            sysRoleResourceRelation.setResourceId(resourceId);
            count += sysRoleResourceRelationMapper.insertSelective(sysRoleResourceRelation);
        }
        return count;
    }

    @Override
    public List<Long> getPermissionList(Long roleId) {
        SysRoleResourceRelationExample example = new SysRoleResourceRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<SysRoleResourceRelation> relations =  sysRoleResourceRelationMapper.selectByExample(example);
        List<Long> resources = new ArrayList<>();
        for (SysRoleResourceRelation relation:relations) {
            resources.add(relation.getResourceId());
        }
        return resources;
    }

    @Override
    public int updatePassword(UpdatePasswordVO updatePasswordVO) {
        if(StrUtil.isEmpty(updatePasswordVO.getUsername())
                ||StrUtil.isEmpty(updatePasswordVO.getOldPassword())
                ||StrUtil.isEmpty(updatePasswordVO.getNewPassword())){
            return -1;
        }
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(updatePasswordVO.getUsername());
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if(CollUtil.isEmpty(sysUsers)){
            return -2;
        }
        SysUser sysUser = sysUsers.get(0);
        if(!passwordEncoder.matches(updatePasswordVO.getOldPassword(),sysUser.getPassword())){
            return -3;
        }
        sysUser.setPassword(passwordEncoder.encode(updatePasswordVO.getNewPassword()));
        sysUserMapper.updateByPrimaryKey(sysUser);
        return 1;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        SysUser sysUser = getUserByName(username);
        if (sysUser != null) {
            SysResourceExample example = new SysResourceExample();
            example.createCriteria().andIdIn(getPermissionList(sysUser.getId()));
            List<SysResource> resourceList = sysResourceMapper.selectByExample(example);
            return new AdminUserDetails(sysUser,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
