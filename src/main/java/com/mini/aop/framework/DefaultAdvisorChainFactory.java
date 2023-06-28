package com.mini.aop.framework;

import com.mini.aop.AdvisedSupport;
import com.mini.aop.Advisor;
import com.mini.aop.MethodMatcher;
import com.mini.aop.PointcutAdvisor;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-27 17:52
 **/

public class DefaultAdvisorChainFactory implements AdvisorChainFactory {


    @Override
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?> targetClass) {
        Advisor[] advisors = config.getAdvisors().toArray(new Advisor[0]);
        List<Object> interceptors = new ArrayList<>(advisors.length);
        Class<?> actualClass = targetClass != null ? targetClass : method.getDeclaringClass();
        for (Advisor advisor : advisors) {
            if(advisor instanceof PointcutAdvisor) {
                PointcutAdvisor pointcutAdvisor = (PointcutAdvisor)advisor;
                if (pointcutAdvisor.getPointcut().getClassFilter().matches(actualClass)) {
                    MethodMatcher methodMatcher = pointcutAdvisor.getPointcut().getMethodMatcher();
                    boolean matches = methodMatcher.matches(method, actualClass);
                    if (matches) {
                        MethodInterceptor interceptor = (MethodInterceptor)advisor.getAdvice();
                        interceptors.add(interceptor);
                    }
                }
            }
        }
        return interceptors;
    }
}


