package com.example.aopHW.controller;

import com.example.aopHW.services.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class DemoController {
    private final DemoService demoService;

    public DemoController(DemoService demoService){
        this.demoService = demoService;
    }

    @GetMapping("/test-error")
    public String testError() {
        demoService.riskyMethod();
        return "This will never execute";
    }
}
