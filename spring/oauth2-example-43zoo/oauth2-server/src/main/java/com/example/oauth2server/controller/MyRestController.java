package com.example.oauth2server.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OAuthクライアント経由ではない、直接アクセス用エンドポイント。
 */
@RestController
public class MyRestController {

    @GetMapping("/rest/hello")
    public String hello(final Authentication auth) {
        final String name = auth == null ? "nullname" : auth.getName();
        return "Hello, " + name + "!";
    }
}
