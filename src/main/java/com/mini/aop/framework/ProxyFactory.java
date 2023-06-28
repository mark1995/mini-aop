package com.mini.aop.framework;

import com.mini.aop.AdvisedSupport;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-28 15:41
 **/

public class ProxyFactory extends AdvisedSupport {

    public ProxyFactory() {}

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy() {
        if (this.isProxyTargetClass() || this.getTargetSource().getTargetClass().length == 0) {
            return new CglibAopProxy(this);
        }
        return new JdkDynamicAopProxy(this);
    }


}


