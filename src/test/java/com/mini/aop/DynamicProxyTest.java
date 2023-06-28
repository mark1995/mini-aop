package com.mini.aop;

import com.mini.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.mini.aop.aspectj.AspectjExpressionPointcut;
import com.mini.aop.common.HelloServiceAfterReturnAdvice;
import com.mini.aop.framework.CglibAopProxy;
import com.mini.aop.framework.JdkDynamicAopProxy;
import com.mini.aop.framework.ProxyFactory;
import com.mini.aop.framework.adapter.AfterReturningAdviceInterceptor;
import com.mini.aop.service.HelloService;
import com.mini.aop.service.HelloServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-27 18:26
 **/

public class DynamicProxyTest {


    private AdvisedSupport advisedSupport;

    @Before
    public void setUp() throws Exception {
        HelloService helloService = new HelloServiceImpl();
        advisedSupport = new ProxyFactory();

        String expression = "execution(* com.mini.aop.service.HelloService.sayHello(..))";
        AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
        pointcutAdvisor.setExpression(expression);
        AfterReturningAdviceInterceptor afterReturningAdviceInterceptor = new AfterReturningAdviceInterceptor(new HelloServiceAfterReturnAdvice());
        pointcutAdvisor.setAdvice(afterReturningAdviceInterceptor);

        TargetSource targetSource = new TargetSource(helloService);
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.addAdvisor(pointcutAdvisor);
    }


    @Test
    public void testDynamicProxy() {
        HelloService proxy = (HelloService)new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.sayHello();
    }

    @Test
    public void testCglibDynamicProxy() {
        HelloService proxy = (HelloService)new CglibAopProxy(advisedSupport).getProxy();
        proxy.sayHello();
    }


    @Test
    public void testProxyFactory() {

        ProxyFactory proxyFactory = (ProxyFactory) advisedSupport;
        proxyFactory.setProxyTargetClass(false);
        HelloService helloService = (HelloService)proxyFactory.getProxy();
        helloService.sayHello();

        proxyFactory.setProxyTargetClass(true);
        ((HelloService) proxyFactory.getProxy()).sayHello();
    }
}


