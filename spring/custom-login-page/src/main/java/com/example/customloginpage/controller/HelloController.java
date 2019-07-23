package com.example.customloginpage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/world")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/user")
    public String user(final Authentication auth) {
        return "Hello, " + auth.getName() + "!";
    }
}
