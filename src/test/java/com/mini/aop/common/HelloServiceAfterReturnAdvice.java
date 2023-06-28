package com.mini.aop.common;

import com.mini.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-28 16:34
 **/

public class HelloServiceAfterReturnAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] arguments, Object target) {
        System.out.println("after advice: do something after the say hello");
        System.out.println("return value: " + returnValue);
    }
}


