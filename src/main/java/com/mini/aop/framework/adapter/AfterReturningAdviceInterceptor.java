package com.mini.aop.framework.adapter;

import com.mini.aop.AfterAdvice;
import com.mini.aop.AfterReturningAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-28 16:38
 **/

public class AfterReturningAdviceInterceptor implements AfterAdvice, MethodInterceptor {

    private AfterReturningAdvice returningAdvice;


    public AfterReturningAdviceInterceptor() {

    }

    public AfterReturningAdviceInterceptor(AfterReturningAdvice returningAdvice) {
        this.returningAdvice = returningAdvice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object retVal = methodInvocation.proceed();
        this.returningAdvice.afterReturning(retVal, methodInvocation.getMethod(),methodInvocation.getArguments(), methodInvocation.getThis());
        return retVal;
    }
}


