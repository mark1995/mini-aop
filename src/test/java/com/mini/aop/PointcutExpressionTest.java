package com.mini.aop;

import com.mini.aop.aspectj.AspectjExpressionPointcut;
import com.mini.aop.service.HelloService;
import org.junit.Test;


import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-27 17:57
 **/

public class PointcutExpressionTest {

    @Test
    public void testPointcutExpression() throws Exception {
        AspectjExpressionPointcut pointcut = new AspectjExpressionPointcut("execution(* com.mini.aop.service.HelloService.*(..))");
        Class<HelloService> clazz = HelloService.class;
        Method sayHello = clazz.getDeclaredMethod("sayHello");
        assertTrue(pointcut.matches(clazz));
        assertTrue(pointcut.matches(sayHello, clazz));

    }
}


