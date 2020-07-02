package com.ecommerce.service.impl;

import com.ecommerce.service.RedisService;
import com.ecommerce.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员管理Service实现类
 * Created by macro on 2018/8/3.
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;

    @Override
    public String generateAuthCode(String telephone) {
        return "aa";
    }

    @Override
    public String verifyAuthCode(String telephone, String authCode) {
        return "aaa";
    }
}
