package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserExample;
import com.example.demo.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    public List<User> selectAllUser() {
        return userMapper.selectAll();
    }

    /**
     * MYBATIS 插入，推荐使用 insertSelective, 这样DB的默认值可以生效
     */
    public void insertService() {
        User user1 = User.builder().name("tom1").age(20).money(3000.0).build();
        User user2 = User.builder().name("jim1").age(18).money(2000.0).build();
        userMapper.insertSelective(user1);
        userMapper.insertSelective(user2);
    }

    public void deleteService(int id) {
        userMapper.deleteByPrimaryKey(id);
    }

    //模拟事务
    @Transactional
    public void changeMoney() {
        User user1 = User.builder().name("tom1").age(20).money(3000.0).id(1).build();
        User user2 = User.builder().name("tom2").age(25).money(4000.0).id(2).build();
        userMapper.updateByPrimaryKeySelective(user1);
        int temp = 1 / 0;
        userMapper.updateByPrimaryKeySelective(user2);
    }


    public PageInfo<User> getUserPage(int page, int size) {
        PageHelper.startPage(page, size);
        List<User> users = userMapper.selectByExample(null);
        PageInfo<User> pageInfo = new PageInfo<>(users);

//        PageHelper.startPage(page, size);
//        UserExample userExample = new UserExample();
//        UserExample.Criteria criteria = userExample.createCriteria();
//        criteria.andNameLike("hicen1" + "%");
//        userExample.setOrderByClause("name desc");
//        List<User> users1 = userMapper.selectByExample(userExample);
//        PageInfo<User> pageInfo2 = new PageInfo<>(users1);
//        log.info("user1>>{}", pageInfo2);

        return pageInfo;
    }

}



