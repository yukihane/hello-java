package com.example.loggingexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MyController {

    @GetMapping
    public String index() {
        return "{}";
    }

    @GetMapping("/exception")
    public String exeption() throws Exception {
        throw new Exception("This is sample exception message!");
    }

    @GetMapping("/myexception")
    public String myexeption() {
        throw new MyException("This is sample exception message!");
    }
}
