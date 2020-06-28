package com.ecommerce.service.impl;

import com.ecommerce.service.RedisService;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public RedisServiceImpl() {
    }

    public void set(String key, String value) {
        this.stringRedisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return (String) this.stringRedisTemplate.opsForValue().get(key);
    }

    public boolean expire(String key, long expire) {
        return this.stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public void remove(String key) {
        this.stringRedisTemplate.delete(key);
    }

    public Long increment(String key, long delta) {
        return this.stringRedisTemplate.opsForValue().increment(key, delta);
    }
}
