package com.mybatisplus.mybatisplusdemo.comtroller;

import com.mybatisplus.mybatisplusdemo.mapper.UserMapper;
import com.mybatisplus.mybatisplusdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：HQ
 * @date ：Created in 2019/7/1 9:48
 * @description：
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/getUser")
    public List<User> getUser(){
        List<User> users = userMapper.selectList(null);
        return users;
    }
}
