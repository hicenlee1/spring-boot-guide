package com.example.springbootmultidatasoruce.db1.mapper;

import com.example.springbootmultidatasoruce.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import tk.mybatis.mapper.common.Mapper;

/**
* Created by Mybatis Generator 2021-03-01
*/

@Qualifier("db1SqlSessionTemplate")
public interface UserMapper extends Mapper<User> {
}