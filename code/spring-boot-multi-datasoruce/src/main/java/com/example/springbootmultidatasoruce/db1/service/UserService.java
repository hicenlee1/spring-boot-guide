package com.example.springbootmultidatasoruce.db1.service;

import com.example.springbootmultidatasoruce.db1.mapper.UserMapper;
import com.example.springbootmultidatasoruce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User selectUser() {
        return userMapper.selectAll().get(0);
    }

}
