package com.example.aopHW.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error ->
                errors.put("error", error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }
}

