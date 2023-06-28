package com.mini.aop.aspectj;

import com.mini.aop.ClassFilter;
import com.mini.aop.MethodMatcher;
import com.mini.aop.Pointcut;
import com.mini.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @description:
 * @author: GXK
 * @create: 2023-06-28 16:31
 **/

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectjExpressionPointcut pointcut;

    private Advice advice;

    private String expression;


    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectjExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}


