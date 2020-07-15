package com.ecommerce.utils;

import com.ecommerce.pojo.SysResource;
import com.ecommerce.pojo.SysUser;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
public class AdminUserDetails implements UserDetails {
    private SysUser sysUser;
    private List<SysResource> resourceList;
    public AdminUserDetails(SysUser sysUser, List<SysResource> resourceList) {
        this.sysUser = sysUser;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (SysResource role : resourceList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getId() + ":" + role.getName());
            list.add(simpleGrantedAuthority);
        }
        return list;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
