package com.example.aopHW.aspects;

import com.example.aopHW.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Slf4j
@Component
public class SecurityLoggingAspect {

    @Before("execution(* com.example.aopHW.controller..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering method: {} with args {}",
                joinPoint.getSignature(),
                joinPoint.getArgs());
    }

    @After("execution(* com.example.aopHW.controller..*(..))")
    public void logAfter(JoinPoint joinPoint){
        log.info("Executed method: {} with args {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @Around("execution(* com.example.aopHW..*(..))")
    public Object validateToken(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Inside @Around advice for {}", joinPoint.getSignature());

        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            log.error("Request attributes are null");
            return joinPoint.proceed();
        }

        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Authorization");

        if (token == null || !token.equals("Bearer SECRET123")) {
            log.warn("Invalid or missing token");
            throw new UnauthorizedException("Invalid Security Token");
        }

        return joinPoint.proceed();
    }

}
