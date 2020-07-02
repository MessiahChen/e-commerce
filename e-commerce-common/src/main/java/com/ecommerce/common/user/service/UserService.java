package com.ecommerce.common.user.service;

import com.ecommerce.common.user.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface UserService {

    int insert(User user);

    int update(User user);

    int delete(String userid);

    User getById(String userid);

    List<User> getAll();

    List<User> getAllByFilter(Map<String,Object> map);
    //pagenum   pagenow  ,pagesize
    PageInfo<User> getAllByFilter(Integer pageNum,Integer pageSize);

    PageInfo<User> getAllByFilter(Integer pageNum,Integer pageSize,Map<String,Object> map);
}
