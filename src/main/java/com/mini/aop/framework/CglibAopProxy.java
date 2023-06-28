package com.mini.aop.framework;

import com.mini.aop.AdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-28 16:17
 **/

public class CglibAopProxy implements AopProxy {

    private final AdvisedSupport advisedSupport;

    public CglibAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }



    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private final AdvisedSupport advisedSupport;

        public DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
            this.advisedSupport = advisedSupport;
        }



        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            // 获取目标对象
            Object target = advisedSupport.getTargetSource().getTarget();
            Class<?> targetClass = target.getClass();
            Object retVal = null;
            List<Object> chain = this.advisedSupport.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
            CglibMethodInvocation cglibMethodInvocation = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy);
            if (chain == null || chain.isEmpty()) {
                retVal = methodProxy.invoke(target, args);
            } else {
                retVal = cglibMethodInvocation.proceed();
            }
            return retVal;
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object proxy, Object target, Method method, Object[] arguments, Class<?> targetClass, List<Object> chains,
                                     MethodProxy methodProxy) {
            super(proxy, target, method, arguments, targetClass, chains);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return super.proceed();
        }
    }
}


