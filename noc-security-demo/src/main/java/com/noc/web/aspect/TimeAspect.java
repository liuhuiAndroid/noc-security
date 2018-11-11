package com.noc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 切片
 */
@Aspect
@Component
public class TimeAspect {

    // @Before()
    // @After()
    // @AfterThrowing()
    // @Around包含上面几种，一般使用@Around
    // spring.io spring-framework Core Technologies 中Aspect Oriented Programming with Spring有Declaring an Aspect有20多个表达式例子
    /**
     *
     * @param pjp ProceedingJoinPoint包含拦截方法的信息
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.noc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        // 方法参数
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        long start = new Date().getTime();
        // 调用方法，真正的执行
        Object object = pjp.proceed();
        System.out.println("time aspect 耗时:" + (new Date().getTime() - start));

        System.out.println("time aspect end");
        return object;
    }

}
