package com.mini.aop.framework;

import com.mini.aop.AdvisedSupport;

import java.lang.reflect.Method;
import java.util.List;

public interface AdvisorChainFactory {

    List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?> targetClass);
}
