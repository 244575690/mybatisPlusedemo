package com.mybatisplus.mybatisplusdemo.task;

import com.mybatisplus.mybatisplusdemo.controller.RedisController;
import com.mybatisplus.mybatisplusdemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ：HQ
 * @date ：Created in 2019/7/2 15:33
 * @description：  集成定时器
 */
@Component  //将此类交给Spring管理
public class UserTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private RedisController redisController;
    @Scheduled(fixedRate = 5000)
    public void getUserById(){
        System.out.println("定时任务执行时间：" + dateFormat.format(new Date()));
        User user = redisController.getUser(1L);
        System.err.println(user.getName()+"wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
    }
}
