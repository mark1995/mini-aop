package com.mini.aop.framework;

import com.mini.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-28 15:44
 **/

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }



    @Override
    public Object getProxy() {
        Object proxy = Proxy.newProxyInstance(advisedSupport.getTargetSource().getTarget().getClass().getClassLoader(),
                advisedSupport.getTargetSource().getTargetClass(), this);
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取目标对象
        Object target = advisedSupport.getTargetSource().getTarget();
        Class<?> targetClass = target.getClass();
        Object retVal = null;
        List<Object> interceptorChains = this.advisedSupport.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
        if (interceptorChains == null || interceptorChains.isEmpty()) {
            return method.invoke(target, args);
        } else {
            // 将拦截器统一封装成ReflectiveMethodInvocation
            MethodInvocation invocation =
                    new ReflectiveMethodInvocation(proxy, target, method, args, targetClass, interceptorChains);
            // Proceed to the joinpoint through the interceptor chain.
            // 执行拦截器链
            retVal = invocation.proceed();
        }
        return retVal;
    }
}


