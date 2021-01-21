package com.example.oauth2statesave;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
@Slf4j
public class SecureController {

    @GetMapping("hello")
    public String hello() {
        final Authentication authn = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: {}", authn);
        return "Hello " + authn.getName() + "!";
    }

    @GetMapping("bye")
    public String bye() {
        final Authentication authn = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: {}", authn);
        return "Bye " + authn.getName() + "!";
    }
}
