package com.mybatisplus.mybatisplusdemo.service;

import com.mybatisplus.mybatisplusdemo.mapper.UserMapper;
import com.mybatisplus.mybatisplusdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ：HQ
 * @date ：Created in 2019/7/1 15:59
 * @description：
 */
@Service
public class Userservice {
    @Autowired
   private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

//    查询所有的用户
    public List<User> queryAll() {
        return userMapper.selectList(null);
    }

    /**
     * 根据主键id获取数据
     * @param id
     * @return
     *
     * 策略：首先通过key值到redis中去查，如果有，就直接从redis中获取，不通过数据库访问；如果redis中没有，就通过访问数据库查询，
     * 然后将查询出来的数据存入redis中
     */
    public User getUserById(long id){
        String key = "user_" + id;
//        获取redis对象
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
//        判断redis中是否存在这个key
        boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){
            long start = System.currentTimeMillis();
//            从redis中获取该key值对于的value值
            System.err.println("=======从rides中获取数据=======");
            User user = valueOperations.get(key);
            long end = System.currentTimeMillis();
            System.out.println("查询redis花费的时间是:" + (end - start)+"s");
            if(user!=null){
                System.err.println(user.getName());
            }

            System.err.println("==========================================");

            return user;
        }else{
            long start = System.currentTimeMillis();
//            访问数据获取数据
            System.err.println("=======从数据库中获取数据=======");
            User user = userMapper.selectById(id);
            long end = System.currentTimeMillis();
            System.out.println("查询mysql花费的时间是:" + (end - start)+"s");
            if(user!=null){
                System.err.println(user.getName());
                //            将获得数据存入缓存中
                valueOperations.set(key, user, 5, TimeUnit.HOURS);
            }
            System.err.println("=======================================");

            return user;
        }
    }

    /**
     * 修改数据
     * @param id
     * @param name
     * @return
     * 策略：修改数据库中的数据，然后通过key值到redis中去查询有没有对于的数据，如果有，将该数据删除，
     * 然后就修改后的数据再存入redis中
     */
    public int updateData(Long id,String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
//        修改数据中的数据
        int i = userMapper.updateById(user);

        if(i!=0){
//        组装redis中的key值
            String key = "user_"+id;
//        获取redis对象
            ValueOperations<String,User> redis = redisTemplate.opsForValue();
            Boolean aBoolean = redisTemplate.hasKey(key);
            if(aBoolean){
                redisTemplate.delete(key);
                System.err.println("删除redis中的数据");
            }
//            查询出修改之后的数据并将该数据存入redis中
            User user1 = userMapper.selectById(id);
            redis.set(key,user1,3,TimeUnit.HOURS);

        }
        return i;
    }

    /**
     * 查询所有的数据
     * @return
     * 策略：
     */
    /*public List<User> getAllUsers(){
           String key  = "users";
//           获取redis对象
        ValueOperations<String,List<User>> ops = redisTemplate.opsForValue();
//        判断redis中是否存在需要查询的数据，如果有，就从redis中获取并返回，如果没有，则到数据库中获取数据，并将获取的数据存入redis中并返回
        Boolean hasKey = redisTemplate.hasKey(key);
        if(hasKey){

        }
    }*/


    /**
     * 删除数据
     * @param id
     * @return
     * 策略：先删除数据库中对应的数据，然后在判断redis中是否存在被删除的数据，如果存在，将redis中的数据也删除掉
     */
    public int deleteUser(Long id){
//        拼接redis中的key值
        String key = "user_"+id;
        int i = userMapper.deleteById(id);
        if(i!=0){
//            判断缓存中是否存在该key值，如果存在，就将redis中的该key值对应的数据也删除掉
//            获取redis对象
            ValueOperations<String,User> ops = redisTemplate.opsForValue();
//            判断是否存在
            Boolean hasKey = redisTemplate.hasKey(key);
            if(hasKey){
                redisTemplate.delete(key);
                System.err.println("删除redis中的数据");
            }
        }
        return i;
    }
}
