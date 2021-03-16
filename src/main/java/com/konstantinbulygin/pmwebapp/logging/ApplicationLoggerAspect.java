package com.konstantinbulygin.pmwebapp.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
    public void log() {
        loger.debug(" ----------------------------- ");
    }
}
