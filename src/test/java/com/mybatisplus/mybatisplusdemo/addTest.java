package com.mybatisplus.mybatisplusdemo;

import com.mybatisplus.mybatisplusdemo.mapper.UserMapper;
import com.mybatisplus.mybatisplusdemo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class addTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void addUser(){
        User user = new User();
        user.setAge(34);
       user.setEmail("22@baomidou.com");
       user.setName("苞米豆");
       user.setRemark("备注信息");
        userMapper.insert(user);
        System.err.println(user.getId());

    }
	
}
