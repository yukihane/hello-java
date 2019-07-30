package com.example.oauth2server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/authenticate")
public class FormLoginController {

    @GetMapping("/identity")
    public String identity() {
        return "login.html";
    }
}
