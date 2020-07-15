package com.ecommerce.service.impl;

import com.ecommerce.dao.SysResourceMapper;
import com.ecommerce.dao.SysUserLoginLogMapper;
import com.ecommerce.dao.SysUserMapper;
import com.ecommerce.dao.SysUserRoleRelationMapper;
import com.ecommerce.pojo.*;
import com.ecommerce.security.util.JwtTokenUtil;
import com.ecommerce.service.UserService;
import com.ecommerce.utils.AdminUserDetails;
import com.ecommerce.vojo.RegisterVO;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

//    @Resource
//    private UmsAdminRoleRelationDao adminRoleRelationDao;
//    @Resource
//    private SysUserPermissionRelationMapper adminPermissionRelationMapper;
//    @Resource
//    private UmsAdminPermissionRelationDao adminPermissionRelationDao;


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

        SysUserRoleRelation sysUserRoleRelation = new SysUserRoleRelation();
        sysUserRoleRelation.setAdminId((long) userId);
        sysUserRoleRelation.setRoleId((long) registerVO.getRoleId());
        sysUserRoleRelationMapper.insertSelective(sysUserRoleRelation);

        return user;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
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
//
//    /**
//     * 根据用户名修改登录时间
//     */
//    private void updateLoginTimeByUsername(String username) {
//        UmsAdmin record = new UmsAdmin();
//        record.setLoginTime(new Date());
//        UmsAdminExample example = new UmsAdminExample();
//        example.createCriteria().andUsernameEqualTo(username);
//        adminMapper.updateByExampleSelective(record, example);
//    }
//
    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public SysUser getItem(Long id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
//
//    @Override
//    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
//        PageHelper.startPage(pageNum, pageSize);
//        UmsAdminExample example = new UmsAdminExample();
//        UmsAdminExample.Criteria criteria = example.createCriteria();
//        if (!StringUtils.isEmpty(keyword)) {
//            criteria.andUsernameLike("%" + keyword + "%");
//            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
//        }
//        return adminMapper.selectByExample(example);
//    }
//
//    @Override
//    public int update(Long id, UmsAdmin admin) {
//        admin.setId(id);
//        UmsAdmin rawAdmin = adminMapper.selectByPrimaryKey(id);
//        if(rawAdmin.getPassword().equals(admin.getPassword())){
//            //与原加密密码相同的不需要修改
//            admin.setPassword(null);
//        }else{
//            //与原加密密码不同的需要加密修改
//            if(StrUtil.isEmpty(admin.getPassword())){
//                admin.setPassword(null);
//            }else{
//                admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//            }
//        }
//        return adminMapper.updateByPrimaryKeySelective(admin);
//    }
//
//    @Override
//    public int delete(Long id) {
//        return adminMapper.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public int updateRole(Long adminId, List<Long> roleIds) {
//        int count = roleIds == null ? 0 : roleIds.size();
//        //先删除原来的关系
//        SysUserRoleRelationExample adminRoleRelationExample = new UmsAdminRoleRelationExample();
//        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
//        adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
//        //建立新关系
//        if (!CollectionUtils.isEmpty(roleIds)) {
//            List<SysUserRoleRelation> list = new ArrayList<>();
//            for (Long roleId : roleIds) {
//                SysUserRoleRelation roleRelation = new SysUserRoleRelation();
//                roleRelation.setAdminId(adminId);
//                roleRelation.setRoleId(roleId);
//                list.add(roleRelation);
//            }
//            adminRoleRelationDao.insertList(list);
//        }
//        return count;
//    }
//
//    @Override
//    public List<UmsRole> getRoleList(Long adminId) {
//        return adminRoleRelationDao.getRoleList(adminId);
//    }
//
    @Override
    public List<SysResource> getResourceList(Long userId) {
        return sysResourceMapper.getResourceList(userId);
    }
//
//    @Override
//    public int updatePermission(Long adminId, List<Long> permissionIds) {
//        //删除原所有权限关系
//        UmsAdminPermissionRelationExample relationExample = new UmsAdminPermissionRelationExample();
//        relationExample.createCriteria().andAdminIdEqualTo(adminId);
//        adminPermissionRelationMapper.deleteByExample(relationExample);
//        //获取用户所有角色权限
//        List<UmsPermission> permissionList = adminRoleRelationDao.getRolePermissionList(adminId);
//        List<Long> rolePermissionList = permissionList.stream().map(UmsPermission::getId).collect(Collectors.toList());
//        if (!CollectionUtils.isEmpty(permissionIds)) {
//            List<UmsAdminPermissionRelation> relationList = new ArrayList<>();
//            //筛选出+权限
//            List<Long> addPermissionIdList = permissionIds.stream().filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
//            //筛选出-权限
//            List<Long> subPermissionIdList = rolePermissionList.stream().filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
//            //插入+-权限关系
//            relationList.addAll(convert(adminId,1,addPermissionIdList));
//            relationList.addAll(convert(adminId,-1,subPermissionIdList));
//            return adminPermissionRelationDao.insertList(relationList);
//        }
//        return 0;
//    }
//
//    /**
//     * 将+-权限关系转化为对象
//     */
//    private List<UmsAdminPermissionRelation> convert(Long adminId,Integer type,List<Long> permissionIdList) {
//        List<UmsAdminPermissionRelation> relationList = permissionIdList.stream().map(permissionId -> {
//            UmsAdminPermissionRelation relation = new UmsAdminPermissionRelation();
//            relation.setAdminId(adminId);
//            relation.setType(type);
//            relation.setPermissionId(permissionId);
//            return relation;
//        }).collect(Collectors.toList());
//        return relationList;
//    }
//
//    @Override
//    public List<UmsPermission> getPermissionList(Long adminId) {
//        return adminRoleRelationDao.getPermissionList(adminId);
//    }
//
//    @Override
//    public int updatePassword(com.macro.mall.dto.UpdateAdminPasswordParam param) {
//        if(StrUtil.isEmpty(param.getUsername())
//                ||StrUtil.isEmpty(param.getOldPassword())
//                ||StrUtil.isEmpty(param.getNewPassword())){
//            return -1;
//        }
//        UmsAdminExample example = new UmsAdminExample();
//        example.createCriteria().andUsernameEqualTo(param.getUsername());
//        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
//        if(CollUtil.isEmpty(adminList)){
//            return -2;
//        }
//        UmsAdmin umsAdmin = adminList.get(0);
//        if(!passwordEncoder.matches(param.getOldPassword(),umsAdmin.getPassword())){
//            return -3;
//        }
//        umsAdmin.setPassword(passwordEncoder.encode(param.getNewPassword()));
//        adminMapper.updateByPrimaryKey(umsAdmin);
//        return 1;
//    }
//
    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        SysUser admin = getUserByName(username);
        if (admin != null) {
            List<SysResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
