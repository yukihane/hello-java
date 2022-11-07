package com.example.thymeleaf_example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping
    public String index() {
        return "index";
    }
}
