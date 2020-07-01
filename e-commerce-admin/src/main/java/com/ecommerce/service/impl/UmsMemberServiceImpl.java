package com.ecommerce.service.impl;

import com.ecommerce.service.RedisService;
import com.ecommerce.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

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

    // TODO: 2020/6/28 这里之后出bug
//    @Value("${redis.key.prefix.authCode}")
//    private String REDIS_KEY_PREFIX_AUTH_CODE;
//    @Value("${redis.key.expire.authCode}")
//    private Long AUTH_CODE_EXPIRE_SECONDS;
//
//    @Override
//    public String generateAuthCode(String telephone) {
//        StringBuilder sb = new StringBuilder();
//        Random random = new Random();
//        for (int i = 0; i < 6; i++) {
//            sb.append(random.nextInt(10));
//        }
//        //验证码绑定手机号并存储到redis
//        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
//        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
//        return "获取验证码成功";
//    }
//
//
//    //对输入的验证码进行校验
//    @Override
//    public String verifyAuthCode(String telephone, String authCode) {
//        if (StringUtils.isEmpty(authCode)) {
//            return "请输入验证码";
//        }
//        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
//        boolean result = authCode.equals(realAuthCode);
//        if (result) {
//            return "验证码校验成功";
//        } else {
//            return "验证码不正确";
//        }
//    }

}
