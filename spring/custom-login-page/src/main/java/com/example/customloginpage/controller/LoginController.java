package com.example.customloginpage.controller;

import com.example.customloginpage.entiry.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @ModelAttribute
    public LoginForm loginForm() {
        return new LoginForm();
    }

    @GetMapping("/hello")
    public String loginHello(@ModelAttribute final LoginForm form) {
        form.setName("hello");
        return "/login/login-hello";
    }

}
