package com.practice.CustomerService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AopConfig {

    @Pointcut("execution(* com.practice.CustomerService..*.*(..))")
    public void customerServiceMethods() {

    }

    @Around("customerServiceMethods()")
    public void beforeHandle(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();
        long timeTaken = System.currentTimeMillis() - start;
        System.out.println("✅ Done: " + pjp.getSignature());
        System.out.println("⏱️ Time Taken: " + timeTaken + "ms");
    }
}
