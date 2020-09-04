package com.example.oidcexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

        final OAuth2User user = ((OAuth2AuthenticationToken) authn).getPrincipal();
        final Object favNum = user.getAttribute("fav-number");
        return "Hello " + authn.getName() + "! Your fav is " + favNum + ".";
    }
}
