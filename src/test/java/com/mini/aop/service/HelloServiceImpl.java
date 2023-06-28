package com.mini.aop.service;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-27 17:59
 **/

public class HelloServiceImpl implements HelloService {


    public String sayHello() {
        System.out.println("Hello");
        return "Hello";
    }
}


