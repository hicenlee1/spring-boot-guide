package com.example.helloworld.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(timeout));
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
