package com.example.aopHW.services;

import com.example.aopHW.exceptions.CustomAppException;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImplementation implements DemoService {
    @Override
    public void riskyMethod() {
        throw new CustomAppException("Something went wrong!");
    }
}
