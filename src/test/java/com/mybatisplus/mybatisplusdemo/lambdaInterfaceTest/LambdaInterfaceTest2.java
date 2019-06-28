package com.mybatisplus.mybatisplusdemo.lambdaInterfaceTest;

import com.mybatisplus.mybatisplusdemo.interfaces.NotReturnHaveOneParamInterface;
import com.mybatisplus.mybatisplusdemo.interfaces.NotReturnHaveParamInterface;
import com.mybatisplus.mybatisplusdemo.interfaces.ReturnInterface;

/**
 * @author ：HQ
 * @date ：Created in 2019/6/17 16:14
 * @description：Lambda表达式精简
 */
public class LambdaInterfaceTest2 {
    public static void main(String[] args) {
        //1、精简参数
        //由于在接口的抽象方法中已经定义了参数的类型和数量，所以在lambda表达式中参数的类型可以省略
        NotReturnHaveParamInterface notReturnHaveParamInterface = (a,b)->{
            System.err.println("我是有多个参数没有返回值");
        };

        //参数小括号
        //如果参数列表中的参数只有一个参数，此时的小括号可以省略掉
        NotReturnHaveOneParamInterface notReturnHaveOneParamInterface = a->{
            System.err.println("1111");
        };

        //方法体的大括号
        //如果方法体中只有一条语句，那么大括号可以省略掉
        NotReturnHaveOneParamInterface notReturnHaveOneParamInterface1 = a->System.err.println("1111");

        //如果方法体中唯一的一条语句是一个返回语句，则大括号可以省略，此时也必须要省略掉return关键字
        ReturnInterface returnInterface = ()-> 10;
    }

}
