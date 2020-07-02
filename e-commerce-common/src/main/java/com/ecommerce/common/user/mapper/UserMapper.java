package com.ecommerce.common.user.mapper;

import com.ecommerce.common.base.BaseMapper;
import com.ecommerce.common.user.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<String, User> {

}
