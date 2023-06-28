package com.mini.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-28 15:56
 **/

public class ReflectiveMethodInvocation implements MethodInvocation {

    private final Object proxy;

    private final Object target;

    private final Method method;

    private final Object[] arguments;

    private final Class<?> targetClass;

    private final List<Object> interceptorsAndDynamicMethodMatchers;

    private int currentInterceptorIndex = -1;

    public ReflectiveMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass, List<Object> chains) {
        this.proxy = proxy;
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.targetClass = targetClass;
        this.interceptorsAndDynamicMethodMatchers = chains;
    }


    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
            return method.invoke(this.target, arguments);
        }
        MethodInterceptor interceptor = (MethodInterceptor)this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
        return interceptor.invoke(this);



    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}


