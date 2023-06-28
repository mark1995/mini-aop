package com.mini.aop;

import java.lang.reflect.Method;

public interface MethodMatcher {

    /**
     * 目标类 匹配 方法
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class<?> targetClass);
}
