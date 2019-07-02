package com.mybatisplus.mybatisplusdemo.controller;

import com.mybatisplus.mybatisplusdemo.mapper.UserMapper;
import com.mybatisplus.mybatisplusdemo.pojo.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：HQ
 * @date ：Created in 2019/7/1 9:48
 * @description：
 */
//@Api(tags = "用户的测试")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @ApiOperation(value="查询数据", notes="")
    @RequestMapping("/getUser")
    public List<User> getUser(){
        List<User> users = userMapper.selectList(null);
        return users;
    }
}
