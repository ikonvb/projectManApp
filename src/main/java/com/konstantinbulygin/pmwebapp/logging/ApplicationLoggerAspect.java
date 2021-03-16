package com.konstantinbulygin.pmwebapp.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger loger = LoggerFactory.getLogger(this.getClass());

    //Where to run
    @Pointcut("within(com.konstantinbulygin.pmwebapp.controllers..*)")
    public void definePackagePointcuts() {
        //empty method just to name the location specified in the pointcut
    }

    //what to run
    @After("definePackagePointcuts()")
    public void logAfter() {
        loger.debug(" ----------------------------- ");
    }

    //what to run
    @Before("definePackagePointcuts()")
    public void logBefore(JoinPoint joinPoint) {
        loger.debug(" \n \n \n");
        loger.debug("********** Before Method Execution ********* \n {}. {} () with arguments[s] = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
        loger.debug("********** After Method Execution *********");
    }

    //what to run
    @Around("definePackagePointcuts()")
    public Object logAround(ProceedingJoinPoint joinPoint) {
        loger.debug(" \n \n \n");
        loger.debug("********** Before Method Execution ********* \n {}. {} () with arguments[s] = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
        loger.debug("********** Before Method Execution *********");

        Object o = null;

        try {
            o = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        loger.debug("********** After Method Execution ********* \n {}. {} () with arguments[s] = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
        loger.debug("********** After Method Execution *********");

        return o;

    }
}
