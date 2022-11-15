package com.redjen.springaop.config;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectPointcutConfig {

    @Pointcut("execution(* com.redjen.springaop.controller.TestController..*(..))")
    public void checkPointcut() {
        System.out.println("pointcut checked");
    }

    @Before("checkPointcut()")
    public void checkBefore() {
        System.out.println("before checked");
    }

    @AfterReturning("checkPointcut()")
    public void checkAfterReturning() {
        System.out.println("after return checked");
    }

    @AfterReturning("execution(* com.redjen.springaop.controller.TestController..*(..))")
    public void checkAfter() {
        System.out.println("after checked with no pointcut");
    }
}
