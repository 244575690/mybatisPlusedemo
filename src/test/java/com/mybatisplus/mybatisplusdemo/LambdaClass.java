package com.mybatisplus.mybatisplusdemo;

public class LambdaClass {
    public static void main(String[] args) {
//        内部类
        Lambda lambda = new Lambda() {
            @Override
            public int getSum(int a, int b) {
                return a + b;
            }
        };
        int sum = lambda.getSum(1, 3);
        System.err.println(sum);

//        使用Lambda表达式来实现接口
        Lambda lambda1 = (a,b)-> {return a+b;};
        int sum1 = lambda1.getSum(3, 4);
        System.err.println(sum1);

    }
}

/**
 *实现类
 */
class LambdaImpl implements Lambda{
    @Override
    public int getSum(int a, int b) {
        return a+b;
    }

}


/**
 * 接口
 */

@FunctionalInterface
interface Lambda{
    int getSum(int a,int b);
//    void getSum1();
}
