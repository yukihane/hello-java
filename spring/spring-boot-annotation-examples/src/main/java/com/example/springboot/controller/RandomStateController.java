package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.property.service.MyService;

@RestController
public class RandomStateController {

    @Autowired
    private MyService service;

    @GetMapping(path = "/state")
    public MyResponse getState() {
        final MyResponse resp = new MyResponse(service.getText());
        return resp;
    }
}
