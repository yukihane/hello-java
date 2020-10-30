package com.example.loggingexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MyController {

    @GetMapping
    public String index() {
        return "{}";
    }
}
