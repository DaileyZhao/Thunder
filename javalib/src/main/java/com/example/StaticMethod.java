package com.example;

/**
 * Created by ZCM on 2017/2/13.
 */

public class StaticMethod {
    public static int staticVar=0;
    public int instanceVar=0;
    public void VarintTest(){
        staticVar++;
        instanceVar++;
        System.out.println("staticVar="+staticVar+"instanceVar="+instanceVar);
    }
}
