	package com.mybatisplus.mybatisplusdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

 @SpringBootApplication
@MapperScan("com.mybatisplus.mybatisplusdemo.mapper")
@EnableSwagger2
 @EnableCaching    //此注解表示启动缓存模式
// @EnableScheduling  //启动定时器，发现注解@Scheduled的任务并后台执行
public class MybatisplusdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusdemoApplication.class, args);
    }

}
