//package com.ecommerce.utils;
//
//import com.ecommerce.pojo.SysMenu;
//import com.ecommerce.pojo.SysUser;
//import lombok.Data;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Data
//public class AdminUserDetails implements UserDetails {
//    private SysUser sysUser;
//    private List<SysMenu> resourceList;
//    public AdminUserDetails(SysUser sysUser,List<SysMenu> resourceList) {
//        this.sysUser = sysUser;
//        this.resourceList = resourceList;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        //返回当前用户的权限
//        return resourceList.stream()
//                .map(role ->new SimpleGrantedAuthority(role.getMenuId()+":"+role.getMenuName()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getPassword() {
//        return sysUser.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return sysUser.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
