package com.example.customloginpage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goodby")
public class GoodbyController {

    @GetMapping("/world")
    public String world() {
        return "Goodby world!";
    }
}
