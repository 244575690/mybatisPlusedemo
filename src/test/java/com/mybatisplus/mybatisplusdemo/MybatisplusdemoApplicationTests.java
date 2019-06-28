package com.mybatisplus.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mybatisplus.mybatisplusdemo.mapper.UserMapper;
import com.mybatisplus.mybatisplusdemo.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Wrapper;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisplusdemoApplicationTests {
@Autowired
 private UserMapper userMapper;
    @Test
    public void contextLoads() {
//        User userList = userMapper.selectById(1);
//        Wrapper
//        List<User> users = userMapper.selectList(null);
//        for (User user : users) {
//            System.err.println(user);
//        }
//        User user = userMapper.selectById(1);
//        System.err.println(user);


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",20);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.err.println(user);
        }

    }

}
