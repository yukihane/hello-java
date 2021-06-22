package com.example.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MyController {

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping
    public String post() {
        return "post";
    }
}
