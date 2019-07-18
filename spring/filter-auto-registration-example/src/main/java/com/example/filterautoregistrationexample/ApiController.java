package com.example.filterautoregistrationexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @GetMapping("/public")
    public String publicApi() {
        log.info("Called: publicApi");
        return "public";
    }

    @GetMapping("/private")
    public String privateApi() {
        log.info("Called: privateApi");
        return "private";
    }
}
