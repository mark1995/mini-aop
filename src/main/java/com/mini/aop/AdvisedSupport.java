package com.mini.aop;

import com.mini.aop.framework.AdvisorChainFactory;
import com.mini.aop.framework.DefaultAdvisorChainFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-27 17:45
 **/

public class AdvisedSupport {

    /**
     * 是否使用cglib 代理
     */
    private boolean proxyTargetClass = true;

    private TargetSource targetSource;

    private MethodMatcher methodMatcher;

    /**
     * 方法hashcode
     * 对应的拦截器
     */
    private transient Map<Integer, List<Object>> methodCaches;

    AdvisorChainFactory advisorChainFactory = new DefaultAdvisorChainFactory();

    private List<Advisor> advisors = new ArrayList<>();

    public AdvisedSupport() {
        methodCaches = new ConcurrentHashMap<>(32);
    }

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public Map<Integer, List<Object>> getMethodCaches() {
        return methodCaches;
    }

    public void setMethodCaches(Map<Integer, List<Object>> methodCaches) {
        this.methodCaches = methodCaches;
    }

    public AdvisorChainFactory getAdvisorChainFactory() {
        return advisorChainFactory;
    }

    public void setAdvisorChainFactory(AdvisorChainFactory advisorChainFactory) {
        this.advisorChainFactory = advisorChainFactory;
    }

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public void setAdvisors(List<Advisor> advisors) {
        this.advisors = advisors;
    }

    public void addAdvisor(Advisor advisor) {
        if (this.advisors == null) {
            this.advisors = new ArrayList<>();
        }
        this.advisors.add(advisor);
    }

    /**
     * 获取方法的拦截器调用链
     * @param method
     * @param targetClass
     * @return
     */
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) {
        int cacheKey = method.hashCode();
        List<Object> cached = this.methodCaches.getOrDefault(cacheKey, null);
        if (cached == null) {
            cached = this.advisorChainFactory.getInterceptorsAndDynamicInterceptionAdvice(this, method, targetClass);
            this.methodCaches.put(cacheKey, cached);
        }
        return cached;
    }
}


