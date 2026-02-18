package com.example.aopHW.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @AfterThrowing(
            pointcut = "execution(* com.example.aopHW..*(..))",
            throwing = "ex"
    )
    public void handleException(JoinPoint joinPoint, Exception ex) {
        log.error("Exception in {} with args {}",
                joinPoint.getSignature(),
                joinPoint.getArgs(),
                ex);
    }
}

