package com.example.oidcexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class HelloController {

    @GetMapping
    public String index() {
        final Authentication authn = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: {}", authn);
        return "Hello " + authn.getName() + "!";
    }
}
