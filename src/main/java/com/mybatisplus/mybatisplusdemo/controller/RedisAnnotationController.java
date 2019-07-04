package com.mybatisplus.mybatisplusdemo.controller;

import com.mybatisplus.mybatisplusdemo.pojo.User;
import com.mybatisplus.mybatisplusdemo.service.Userservice;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：HQ
 * @date ：Created in 2019/7/4 10:03
 * @description：使用redis的注解实现缓存
 *
 * 三个注解的解释在每个方法上，这里要注意这个三个注解对应的方法上的返回值必须一样，不然的话就会报类型转换错误
 */
@RestController
@RequestMapping("/annotation")
public class RedisAnnotationController {
    @Autowired
    private Userservice uservice;

    /**
     * 根据主键id查询数据
     *   动态设置参数id为缓存结果的key
     *   Cacheable注解会先检查key存不存在，不存在就执行方法并缓存结果后返回
     */
    @ApiOperation(value="根据数据的主键id查询对应的数据", notes="")
    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    @Cacheable(value = "users",key="#id")
    public User getUser(@RequestParam("id") Long id){
        User user = uservice.getOneUserById(id);
        return user;
    }

    /**
     * CachePut注解会不管key存不存在，都会更新缓存,这里有一个坑，
     * 更新方法的返回值必须要和查询方法的返回值一样，不然会报转换错误
     * @param id
     * @param name
     * @param age
     * @return
     */
    @ApiOperation(value="根据数据的主键id修改对应的数据", notes="")
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @CachePut(value = "users", key = "#id")
    public User updateUser(@RequestParam("id")Long id,@RequestParam("name")String name,@RequestParam("age")Integer age){
        Integer integer = uservice.updateUserById(id, name, age);
        if(integer!=0){
            return uservice.getOneUserById(id);
        }else{
            return new User();
        }
    }

    /**
     * CacheEvict注解会删除数据库中的对应的数据，并根据key值删除缓存中的对应的数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @CacheEvict(value = "users", key = "#id")
    public User deleteUser(@RequestParam("name")Long id){
        //CacheEvict注解会删除缓存
        int i = uservice.deleteUserById(id);
        if(i!=0){
            return new User();
        }else{
            return null;
        }
    }
}
