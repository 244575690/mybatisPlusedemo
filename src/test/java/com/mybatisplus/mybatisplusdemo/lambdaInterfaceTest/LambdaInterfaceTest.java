package com.mybatisplus.mybatisplusdemo.lambdaInterfaceTest;

import com.mybatisplus.mybatisplusdemo.interfaces.*;

/**
 * @author ：HQ
 * @date ：Created in 2019/6/17 15:41
 * @description：Lambda的基本语法
 *          //lambda是一个匿名函数，即没有方法名
 *         //lambda的基本语法：参数列表+方法体
 *         //():参数列表
 *         //{}：用来描述方法体
 *         //->:lambda的运算符，读作goes to
 *
 */
public class LambdaInterfaceTest {
    public static void main(String[] args) {
        //lambda是一个匿名函数，即没有方法名
        //lambda的基本语法：参数列表+方法体
        //():参数列表
        //{}：用来描述方法体
        //->:lambda的运算符，读作goes to

        /**
         *无参无返回
         */
        NotReturnInterface notReturnInterface = () ->{
            System.err.println("无参无返回");
        };
        notReturnInterface.test();

        /**
         * 无返回值有一个参数
         */
        NotReturnHaveOneParamInterface notReturnHaveOneParamInterface = (a) ->{
           int b = a+1;
           System.err.println(b);
        };
        notReturnHaveOneParamInterface.test(2);


        /**
         * 无返回值有多个参数
         */
        NotReturnHaveParamInterface notReturnHaveParamInterface = (a,b) ->{
            int c = a+b;
            System.err.println(c);
        };
        notReturnHaveParamInterface.test(5,6);

        /**
         * 无参有返回
         */
        ReturnInterface returnInterface = ()->{
            return 100;
        };
        int test = returnInterface.test();
        System.err.println(test);
        /**
         * 一个参数有返回
         */
        ReturnHaveOneParamInterface returnHaveOneParamInterface = (a) ->{
            int b = a+10;
            return b;
        };
        int test1 = returnHaveOneParamInterface.test(20);
        System.err.println(test1);

        /**
         *多个参数有返回
         */
        ReturnHaveParamsInterface returnHaveParamsInterface = (a,b) ->{
            int c = a+b;
            return c;
        };
        int test2 = returnHaveParamsInterface.test(30, 60);
        System.err.println(test2);
    }
}
