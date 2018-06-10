package com.springmvc.currencyCalculatorApp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.springmvc.currencyCalculatorApp.controllers.*.*(..))")
    private void forControllersPackage() {
    }

    @Pointcut("execution(* com.springmvc.currencyCalculatorApp.services.*.*(..))")
    private void forServicesPackage() {
    }

    @Pointcut("execution(* com.springmvc.currencyCalculatorApp.dao.*.*(..))")
    private void forDAOPackage() {
    }

    @Pointcut("forControllersPackage() || forServicesPackage() || forDAOPackage()")
    private void forApplication() {}

    @Before("forApplication()")
    public void before(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("INFO: Calling method: " + methodName);
    }

    @AfterReturning(pointcut = "forApplication()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        logger.info("INFO: Returning from method: " + methodName);
        logger.info("INFO: Data returned: " + result);
    }

}
