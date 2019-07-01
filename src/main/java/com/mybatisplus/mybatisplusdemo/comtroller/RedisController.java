package com.mybatisplus.mybatisplusdemo.comtroller;

import com.mybatisplus.mybatisplusdemo.pojo.User;
import com.mybatisplus.mybatisplusdemo.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：HQ
 * @date ：Created in 2019/7/1 10:55
 * @description：集成redis并测试
 */
@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private Userservice uservice;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("addRedis")
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
    @RequestMapping("getUserByRedis")
    public User getUser(Long id){
        User user = uservice.getUserById(id);
        return user;
    }

    /**
     * 修改数据，并将redis中的数据也修改掉
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("updataUser")
    public int updataUser(Long id,String name){
       return uservice.updateData(id,name);
    }

}
