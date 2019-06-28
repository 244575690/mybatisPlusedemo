package com.mybatisplus.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mybatisplus.mybatisplusdemo.mapper.UserMapper;
import com.mybatisplus.mybatisplusdemo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class selectTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    //通过主键查询数据
    public void selectById(){
        User user = userMapper.selectById(1);
        System.err.println(user);
    }
    @Test
    //通过多个主键查询数据
    public void selectByIds(){
        ArrayList<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        List<User> users = userMapper.selectBatchIds(list);
        users.forEach(user ->System.err.println(user));
    }
    @Test
    //通过Map查询数据(Map中存的是要查询的条件以及条件所对应的的值，注意：map中的键是数据库中的对应的列，不是实体中的属性名)
    public void selectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("age",22);
        map.put("name","张三");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(user ->System.err.println(user));
    }
    @Test
    //通过条件构造器构造条件进行查询
    public void selectByWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        查询age大于20，并且按照age大小倒叙排列，如果age相等，就按照id的大小正序排列
//        QueryWrapper<User> userQueryWrapper = wrapper.ge("age", 20).orderByDesc("age").orderByAsc("id");
//        查询age大于30或者mame包含T的数据
        QueryWrapper<User> userQueryWrapper = wrapper.ge("age", 30).or().like("name","T");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(user -> System.err.println(user));
    }
    @Test
    //通过条件构造器构造条件进行查询
    public void selectByWrapper2(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        查询age大于20，并且按照age大小倒叙排列，如果age相等，就按照id的大小正序排列
//        QueryWrapper<User> userQueryWrapper = wrapper.ge("age", 20).orderByDesc("age").orderByAsc("id");
//        查询age大于30或者mame包含T的数据
        QueryWrapper<User> userQueryWrapper = wrapper.ge("age", 30).or().like("name","T");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(user -> System.err.println(user));
    }

    @Test
    //通过条件构造器构造条件进行查询
    public void selectByWrapper3(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

//        QueryWrapper<User> userQueryWrapper = wrapper.apply("date_format(time,'%Y-%m-%d') = {0}","2019-06-12 or true or true");
        /**
         *  查询指定时间的数据，date_format(time,'%Y-%m-%d')是一个将时间格式化的函数，格式化成yyyy-MM-dd的格式，
         *  去除后面的时分秒。这里要注意，
         *   后面的{0}一定要加，不然会造成sql注入
         *   如上面的那个条件构造，如果不加{0}，就会将2019-06-12 or true or true这一坨直接拼接到sql语句后面，造成查询数据错误。
         *   加了{0}，不是用拼接了，是直接将2019-06-12 or true or true作为一个值，这样就不会造成sql注入了
         */

        QueryWrapper<User> userQueryWrapper = wrapper.apply("date_format(time,'%Y-%m-%d') = {0}","2019-06-12");
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(user -> System.err.println(user));
    }
}


