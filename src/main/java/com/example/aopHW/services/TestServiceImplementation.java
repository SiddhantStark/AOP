package com.example.aopHW.services;

import com.example.aopHW.annotation.TrackExecutionTime;
import com.example.aopHW.util.ExecutionTimeRegistry;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TestServiceImplementation implements TestService {

    @Override
    public String hello() {
        return "Hello AOP";
    }

    @Override
    @TrackExecutionTime
    public String helloWorld() {
        return "Hello World";
    }

    public Map<String, Long> getRecords(){
        return ExecutionTimeRegistry.getReport();
    }
}
