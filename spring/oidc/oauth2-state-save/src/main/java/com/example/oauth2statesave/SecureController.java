package com.example.oauth2statesave;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class SecureController {

    @GetMapping("hello")
    public String hello() {
        final Authentication authn = SecurityContextHolder.getContext().getAuthentication();
        return "Hello " + authn.getName() + "!";
    }

    @GetMapping("bye")
    public String bye() {
        final Authentication authn = SecurityContextHolder.getContext().getAuthentication();
        return "Bye " + authn.getName() + "!";
    }
}
