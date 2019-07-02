package com.mybatisplus.mybatisplusdemo.controller;

import com.mybatisplus.mybatisplusdemo.pojo.User;
import com.mybatisplus.mybatisplusdemo.service.Userservice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：HQ
 * @date ：Created in 2019/7/1 10:55
 * @description：集成redis并测试
 */
//@Api(tags = "整合redis的测试")
@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private Userservice uservice;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @ApiOperation(value="添加数据到redis中", notes="")
    @PostMapping("addRedis")
    public String addRedis(String key,String vale){
        // 保存字符串
        try {
            stringRedisTemplate.opsForValue().set(key,vale);
            return "添加成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败";
        }
    }


    /**
     * 根据主键id查询数据
     */
    @ApiOperation(value="根据数据的主键id查询对应的数据", notes="")
    @RequestMapping(value = "/getUserByRedis",method = RequestMethod.POST)
    public User getUser(@RequestParam("id") Long id){
        User user = uservice.getUserById(id);
        return user;
    }

    /**
     * 修改数据，并将redis中的数据也修改掉
     * @param id
     * @param name
     * @return
     */
    @ApiOperation(value="修改数据", notes="")
    @PostMapping("updataUser")
    public int updataUser(Long id,String name){
       return uservice.updateData(id,name);
    }


    /**
     * 删除对应的数据
     * @param id
     * @return
     */
    @ApiOperation(value="删除数据", notes="")
    @GetMapping("/deleteData")
    public int deleteData(Long id){
        return uservice.deleteUser(id);
    }

}
