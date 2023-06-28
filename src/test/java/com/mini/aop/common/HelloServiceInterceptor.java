package com.mini.aop.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-28 15:27
 **/

public class HelloServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("do something before the earth earth explodes");
        Object result = methodInvocation.proceed();
        System.out.println("after earth explode");
        return result;
    }
}


