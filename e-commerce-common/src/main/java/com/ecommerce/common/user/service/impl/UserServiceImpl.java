package com.ecommerce.common.user.service.impl;

import com.ecommerce.common.user.entity.User;
import com.ecommerce.common.user.mapper.UserMapper;
import com.ecommerce.common.user.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int delete(String userid) {
        return userMapper.delete(userid);
    }

    @Override
    public User getById(String userid) {
        return userMapper.getById(userid);
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public List<User> getAllByFilter(Map<String, Object> map) {
        return userMapper.getAllByFilter(map);
    }

    @Override
    public PageInfo<User> getAllByFilter(Integer pageNum, Integer pageSize) {
        return this.getAllByFilter(pageNum,pageSize, new HashMap());
    }

    @Override
    public PageInfo<User> getAllByFilter(Integer pageNum, Integer pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNum,pageSize,true);
        List<User> users = userMapper.getAllByFilter(map);
        return new PageInfo<>(users);
    }
}
