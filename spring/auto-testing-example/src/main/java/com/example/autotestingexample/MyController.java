package com.example.autotestingexample;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MyController {

    @GetMapping("/")
    public String hello() {
        return "index";
    }
}
