package com.mini.aop;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-27 17:42
 **/

public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget(){
        return target;
    }
}


