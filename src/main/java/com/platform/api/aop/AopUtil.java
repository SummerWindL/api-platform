package com.platform.api.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Advance
 * @date 2022年07月29日 18:11
 * @since V1.0.0
 */
@Aspect
@Slf4j
@Component
public class AopUtil {

    @Pointcut("execution(* com.platform.api.controller.HelloController.hello(..))")//
    protected void doCompletePoint(){

    }

    /**
     * @param joinPoint
     * @Before 前置通知  通知方法在切入点前执行
     * @AfterReturning 后置通知 通知方法在切入点后执行
     * @AfterThrowing 异常通知 通知方法在抛出异常时执行
     * @After 最终通知 通知方法无论是否有异常 ，最终都会执行
     * @Around 通知方法在切入点之前，之后都执行
     */
    @AfterReturning(value = "doCompletePoint()")
    public void afterExcute(JoinPoint joinPoint) {
        log.info("========> {}","执行了切面");
    }
    @After(value = "doCompletePoint()")
    public void afterExcute1(JoinPoint joinPoint) {
        log.info("========> {}","执行了切面1");
    }
}
