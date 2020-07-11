package com.ecommerce.service.impl;

import com.ecommerce.common.exception.BusinessException;
import com.ecommerce.dao.*;
import com.ecommerce.pojo.*;
import com.ecommerce.service.AdminService;
import com.ecommerce.utils.AdminUserDetails;
import com.ecommerce.vojo.LoginBackVO;
import com.ecommerce.vojo.RegisterVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
    
//    @Resource
//    UserDetailsService userDetailsService;
//    @Resource
//    JwtTokenUtil jwtTokenUtil;
//    @Resource
//    PasswordEncoder passwordEncoder;
    @Resource
    SysUserMapper sysUserMapper;
    @Resource
    DsrDropshipperMapper dsrDropshipperMapper;
    @Resource
    ManManufacturerMapper manManufacturerMapper;
    @Resource
    UllUserLoginLogoutLogMapper ullUserLoginLogoutLogMapper;
    
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    //TODO 用途未知
    @Override
    public SysUser getAdminByUsername(String username) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysUser> adminList = sysUserMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public Boolean register(RegisterVO registerVO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(registerVO, sysUser);
        //查询是否有相同用户名的用户
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUsernameEqualTo(sysUser.getUsername());
        List<SysUser> SysUserList = sysUserMapper.selectByExample(example);
        if (SysUserList.size() > 0) {
            throw BusinessException.USERNAME_DUPLICATE;
        }
        //将密码进行加密操作
        sysUser.setPassword(DigestUtils.sha1Hex(sysUser.getPassword()));
//        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));

        int manBuyerId;
        //插入公司表/借卖方表
        if (registerVO.getRoleId().equals("BVO")){
            DsrDropshipper dsrDropshipper = new DsrDropshipper();
            dsrDropshipper.setName(registerVO.getName());
            dsrDropshipper.setRegisterDate(new Date());
            manBuyerId = dsrDropshipperMapper.insertSelective(dsrDropshipper);
        }else {
            ManManufacturer manManufacturer = new ManManufacturer();
            manManufacturer.setNameCn(registerVO.getName());
            manManufacturer.setCreationDate(new Date());
            manBuyerId = manManufacturerMapper.insertSelective(manManufacturer);
        }
        sysUser.setManBuyerId(manBuyerId);
        return sysUserMapper.insertSelective(sysUser) == 1;
    }

    @Override
    public LoginBackVO login(String username, String password) {
        LoginBackVO loginBackVO = new LoginBackVO();
        try {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String token = jwtTokenUtil.generateToken(userDetails);

            SysUserExample example = new SysUserExample();
            example.createCriteria().andUsernameEqualTo(username);
            List<SysUser> users = sysUserMapper.selectByExample(example);
            if (users.isEmpty()) {
                throw BusinessException.USERNAME_NOT_EXISTS;
            }
            SysUser user = users.get(0);
            if (!password.equals(DigestUtils.sha1Hex(user.getPassword()))) {
                throw new BadCredentialsException("密码不正确");
            }
//            loginBackVO.setToken(token);
            BeanUtils.copyProperties(user,loginBackVO);
//            if (token == null) {
//                throw new BadCredentialsException("token值为空");
//            }
            //插入记录
            UllUserLoginLogoutLog ullUserLoginLogoutLog = new UllUserLoginLogoutLog();
//            ullUserLoginLogoutLog.setToken(token);
            ullUserLoginLogoutLog.setUsiId(user.getUserId());
            ullUserLoginLogoutLog.setOperatingType("1");
            ullUserLoginLogoutLog.setOperatingDate(new Date());
            ullUserLoginLogoutLogMapper.insertSelective(ullUserLoginLogoutLog);

        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return loginBackVO;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        SysUser sysUser = getAdminByUsername(username);
        if (sysUser != null) {
            return new AdminUserDetails(sysUser);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }


//    @Override
//    public List<UmsPermission> getPermissionList(Long adminId) {
//        return adminRoleRelationDao.getPermissionList(adminId);
//    }

}
