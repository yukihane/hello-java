package com.github.yukihane.samplelinewebapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping("/line")
    public OAuth2User welcome(@AuthenticationPrincipal final OAuth2User user) {
        return user;
    }
}
