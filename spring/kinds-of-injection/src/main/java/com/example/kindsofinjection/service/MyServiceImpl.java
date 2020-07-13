package com.example.kindsofinjection.service;

import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

    @Override
    public String greet() {
        return "Hello!";
    }
}
