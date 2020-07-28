package com.example.tetteispringexample.login.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @GetMapping("loginForm")
    String loginForm() {
        return "login/loginForm";
    }
}
