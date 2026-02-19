package com.example.aopHW.services;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImplementation implements TestService {

    @Override
    public String hello() {
        return "";
    }
}
